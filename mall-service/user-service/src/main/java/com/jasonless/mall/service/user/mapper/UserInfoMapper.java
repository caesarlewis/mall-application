package com.jasonless.mall.service.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jasonless.mall.user.api.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author LiuShiZeng
 * 2021/4/12
 * 写注释吧
 */
@Repository
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
