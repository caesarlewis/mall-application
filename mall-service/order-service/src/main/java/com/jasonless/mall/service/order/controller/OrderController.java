package com.jasonless.mall.service.order.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.jasonless.mall.common.util.ResponseCode;
import com.jasonless.mall.common.util.ResponseResult;
import com.jasonless.mall.order.api.model.Order;
import com.jasonless.mall.order.api.model.OrderRefund;
import com.jasonless.mall.service.order.pay.WeixinPayParam;
import com.jasonless.mall.service.order.service.OrderService;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private WeixinPayParam weixinPayParam;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /***
     * 添加订单
     */
    @PostMapping
    public ResponseResult add(@RequestBody Order order, HttpServletRequest request) throws Exception {
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
        //支付信息封装
        if(bo){
            //加密字符
            String ciptext = weixinPayParam.weixinParam(order,request);
            return ResponseResult.ok(ciptext);
        }
        return ResponseResult.error(ResponseCode.SYSTEM_ERROR);
    }

    /***
     * 取消订单
     */
    @PutMapping(value = "/refund/{id}")
    public ResponseResult refund(@PathVariable(value = "id")String id, HttpServletRequest request) throws Exception {
        String userName = "test";

        //查询商品信息
        Order order = orderService.getById(id);

        //已支付，待发货，才允许取消订单
        if(order.getOrderStatus().intValue()==1 && order.getPayStatus().intValue()==1){
            //退款记录
            OrderRefund orderRefund = new OrderRefund(
                    IdWorker.getIdStr(),
                    order.getId(),
                    0,//0 整个订单退款，1 单个明细退款
                    null,
                    userName,
                    0,//状态，0：申请退款，1：退款成功，2：退款失败
                    new Date(),
                    order.getMoneys()   //退款金额
            );

            //发送事务消息[退款加密信息]
            Message message = MessageBuilder.withPayload(weixinPayParam.weixinRefundParam(order,orderRefund.getId())).build();
            TransactionSendResult transactionSendResult = rocketMQTemplate.sendMessageInTransaction("refundtx", "refund", message, orderRefund);
            if(transactionSendResult.getSendStatus()== SendStatus.SEND_OK){
                return ResponseResult.error("申请退款成功，等待退款！");
            }
            return ResponseResult.error("不符合取消订单条件，无法退货！");
        }
        return ResponseResult.error("订单已发货，或无法退货！");
    }

}
