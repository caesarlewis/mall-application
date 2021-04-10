package com.jasonless.mall.service.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jasonless.mall.order.api.model.Order;
import com.jasonless.mall.order.api.model.OrderRefund;

/**
 * @author Jasonless
 * @date 2020/12/14
 */
public interface OrderService extends IService<Order> {

    //添加订单
    Boolean add(Order order);

    /**
     * 支付后，修改订单状态
     * @param id
     * @return
     */
    int updateAfterPayStatus(String id);

    /****
     * 申请退款（取消订单）
     * @return
     */
    int refund(OrderRefund orderRefund);

    /****
     * 退款申请成功
     * @param out_trade_no：订单号
     * @param out_refund_no：退款记录订单号
     */
    void updateRefundStatus(String out_trade_no,String out_refund_no);

    /***
     * 退款申请失败，修改退款记录状态
     * @param out_refund_no
     */
    void updateRefundFailStatus(String out_refund_no);

}
