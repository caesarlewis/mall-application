package com.jasonless.mall.service.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jasonless.mall.order.api.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Jasonless
 * @date 2020/12/14
 */
@Repository
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
