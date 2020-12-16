package com.jasonless.mall.service.order.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.jasonless.mall.common.util.ResponseCode;
import com.jasonless.mall.common.util.ResponseResult;
import com.jasonless.mall.order.api.model.Order;
import com.jasonless.mall.service.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Jasonless
 * @date 2020/12/14
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /***
     * 添加订单
     */
    @PostMapping
    public ResponseResult add(@RequestBody Order order){
        String userName = "test";
        order.setUsername(userName);
        order.setCreateTime(new Date());
        order.setUpdateTime(order.getCreateTime());
        order.setId(IdWorker.getIdStr());
        order.setOrderStatus(0);
        order.setPayStatus(0);
        order.setIsDelete(0);

        //添加订单
        Boolean bo = orderService.add(order);
        return bo? ResponseResult.ok():ResponseResult.error(ResponseCode.SYSTEM_ERROR);
    }

}
