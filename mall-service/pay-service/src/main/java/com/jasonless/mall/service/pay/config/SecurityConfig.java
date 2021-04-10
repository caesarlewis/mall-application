package com.jasonless.mall.service.pay.config;

import com.jasonless.mall.common.util.Signature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiuShiZeng
 * 2021/4/10
 * 写注释吧
 */
@Configuration
public class SecurityConfig {
    //秘钥
    @Value("${payconfig.aes.skey}")
    private String skey;

    //验签加盐值
    @Value("${payconfig.aes.salt}")
    private String salt;

    /***
     * 验签对象
     */
    @Bean(value = "signature")
    public Signature signature(){
        return new Signature(skey,salt);
    }
}
