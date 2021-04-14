package com.jasonless.mall.service.permission.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasonless.mall.api.permission.model.Permission;
import com.jasonless.mall.service.permission.mapper.PermissionMapper;
import com.jasonless.mall.service.permission.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author LiuShiZeng
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;


    /***
     * 根据匹配方式查找
     * @param i
     * @return
     */
    @Override
    public List<Permission> findByMatch(int i) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<Permission>();
        queryWrapper.eq("url_match",i);
        return permissionMapper.selectList(queryWrapper);
    }

    /**
     * 所有角色的权限
     * @return
     */
    @Override
    public List<Map<Integer, Integer>> allRolePermissions() {
        return permissionMapper.allRolePermissions();
    }

}
