package com.jasonless.mall.service.permission.init;

import com.jasonless.mall.api.permission.model.Permission;
import com.jasonless.mall.service.permission.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author LiuShiZeng
 * 2021/4/12
 * 写注释吧
 */
@Component
public class InitPermission implements ApplicationRunner {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RedisTemplate redisTemplate;

    /***
     * 初始化
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        //根据匹配方式查找
        List<Permission> permissionsMatch0 = permissionService.findByMatch(0);
        List<Permission> permissionsMatch1 = permissionService.findByMatch(1);

        //查询所有角色权限
        List<Map<Integer,Integer>> rolePermissions = permissionService.allRolePermissions();
        //角色权限处理
        Map<String, Set<Permission>> roleMap = rolePermissionFilter(rolePermissions, permissionsMatch0, permissionsMatch1);

        //将所有权限和角色权限存入到Redis缓存
        redisTemplate.boundHashOps("RolePermissionAll").put("PermissionListMatch0",permissionsMatch0);
        redisTemplate.boundHashOps("RolePermissionAll").put("PermissionListMatch1",permissionsMatch1);
        redisTemplate.boundHashOps("RolePermissionMap").putAll(roleMap);
    }


    /***
     * 角色权限过滤
     * @param rolePermissions   : 角色权限映射关系
     * @param permissionsMatch0 ：所有完全匹配路径
     * @param permissionsMatch1 ：所有通配符匹配路径
     * @return
     */
    public Map<String,Set<Permission>> rolePermissionFilter(List<Map<Integer,Integer>> rolePermissions,
                                                            List<Permission> permissionsMatch0,
                                                            List<Permission> permissionsMatch1){
        //角色权限关系  key=roleid,value=List<Permission>
        Map<String, Set<Permission>> rolePermissionMapping = new HashMap<String,Set<Permission>>();

        //关系循环处理
        for (Map<Integer, Integer> rolePermissionMap : rolePermissions) {
            Integer rid = rolePermissionMap.get("rid");  //角色ID
            Integer pid = rolePermissionMap.get("pid");  //权限ID
            String key0 = "Role_0_"+rid.intValue();
            String key1 = "Role_1_"+rid.intValue();

            //获取当前角色拥有的权限
            Set<Permission> permissionSet0 = rolePermissionMapping.get(key0);
            Set<Permission> permissionSet1 = rolePermissionMapping.get(key1);

            //防止空指针
            permissionSet0=permissionSet0==null? new HashSet<Permission>(): permissionSet0;
            permissionSet1=permissionSet1==null? new HashSet<Permission>(): permissionSet1;

            //循环完全匹配路径
            for (Permission permission : permissionsMatch0) {
                if(permission.getId().intValue()==pid.intValue()){
                    permissionSet0.add(permission);
                    break;
                }
            }
            //循环通配符匹配路径
            for (Permission permission : permissionsMatch1) {
                if(permission.getId().intValue()==pid.intValue()){
                    permissionSet1.add(permission);
                    break;
                }
            }
            //将数据添加到rolePermissionMapping中
            if(permissionSet0.size()>0){
                rolePermissionMapping.put(key0,permissionSet0);
            }
            if(permissionSet1.size()>0){
                rolePermissionMapping.put(key1,permissionSet1);
            }
        }
        return rolePermissionMapping;
    }
}
