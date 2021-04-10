package com.jasonless.mall.service.pay.config;

import com.github.wxpay.sdk.WXPay;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiuShiZeng
 * 2021/4/10
 * 写注释吧
 */
@Configuration
public class WeixinPayBeanConfig {

    /****
     * 微信支付SDK对象
     * @param weixinPayConfig
     * @return
     * @throws Exception
     */
    @Bean
    public WXPay wxPay(WeixinPayConfig weixinPayConfig) throws Exception {
        return new WXPay(weixinPayConfig);
    }
}
