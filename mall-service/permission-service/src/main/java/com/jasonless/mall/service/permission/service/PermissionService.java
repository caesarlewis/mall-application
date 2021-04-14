package com.jasonless.mall.service.permission.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jasonless.mall.api.permission.model.Permission;

import java.util.List;
import java.util.Map;



/**
 * @author LiuShiZeng
 */
public interface PermissionService extends IService<Permission> {

    //不同匹配方式的权限
    List<Permission> findByMatch(int i);

    //所有角色的权限映射
    List<Map<Integer, Integer>> allRolePermissions();
}
