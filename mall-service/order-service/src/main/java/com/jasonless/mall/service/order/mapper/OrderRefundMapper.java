package com.jasonless.mall.service.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jasonless.mall.order.api.model.OrderRefund;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author LiuShiZeng
 * 2021/4/10
 * 写注释吧
 */
@Repository
@Mapper
public interface OrderRefundMapper extends BaseMapper<OrderRefund> {
}
