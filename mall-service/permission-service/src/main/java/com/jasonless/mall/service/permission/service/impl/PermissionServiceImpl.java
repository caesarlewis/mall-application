package com.jasonless.mall.service.permission.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupaoedu.vip.mall.permission.mapper.PermissionMapper;
import com.gupaoedu.vip.mall.permission.service.PermissionService;
import org.springframework.stereotype.Service;

import java.security.Permission;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
}
