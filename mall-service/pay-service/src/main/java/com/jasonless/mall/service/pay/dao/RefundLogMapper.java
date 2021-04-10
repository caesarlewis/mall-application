package com.jasonless.mall.service.pay.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jasonless.mall.api.pay.model.RefundLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author LiuShiZeng
 * 2021/4/10
 * 写注释吧
 */
@Mapper
@Repository
public interface RefundLogMapper extends BaseMapper<RefundLog> {
}
