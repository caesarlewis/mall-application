package com.jasonless.mall.service.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jasonless.mall.order.api.model.Order;

/**
 * @author Jasonless
 * @date 2020/12/14
 */
public interface OrderService extends IService<Order> {

    //添加订单
    Boolean add(Order order);

}
