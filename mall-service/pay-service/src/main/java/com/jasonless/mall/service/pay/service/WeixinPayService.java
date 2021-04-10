package com.jasonless.mall.service.pay.service;

import com.jasonless.mall.api.pay.model.PayLog;

import java.util.Map;

/**
 * @author LiuShiZeng
 * 2021/4/10
 * 写注释吧
 */
public interface WeixinPayService {

    /***
     * 统一下单，获取支付二维码
     */
    Map<String,String> preOrder(Map<String,String> dataMap) throws Exception;

    /***
     * 支付结果查询
     * @param outno
     * @return
     */
    PayLog result(String outno) throws Exception;


    /***
     * 退款
     * @param map
     * @return
     */
    Map<String, String>  refund(Map<String, String> map) throws Exception;

}
