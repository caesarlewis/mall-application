package com.jasonless.mall.service.permission.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.management.relation.RoleInfo;

/**
 * @author LiuShiZeng
 */
@Repository
@Mapper
public interface RoleInfoMapper extends BaseMapper<RoleInfo> {
}
