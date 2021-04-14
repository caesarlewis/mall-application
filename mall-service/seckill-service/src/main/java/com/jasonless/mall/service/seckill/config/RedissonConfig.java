package com.jasonless.mall.service.seckill.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiuShiZeng
 * 2021/4/12
 * 写注释吧
 */
@Configuration
public class RedissonConfig {

    /***
     * 创建RedissonClient客户端
     * @return
     */
    @Bean
    public RedissonClient redisson(){
        Config config = new Config();
        //单机模式
        config.useSingleServer().setAddress("redis://192.168.100.130:6379");
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}
