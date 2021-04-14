package com.jasonless.mall.service.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasonless.mall.service.user.mapper.UserInfoMapper;
import com.jasonless.mall.service.user.service.UserInfoService;
import com.jasonless.mall.user.api.model.UserInfo;
import org.springframework.stereotype.Service;

/**
 * @author LiuShiZeng
 * 2021/4/12
 * 写注释吧
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
}
