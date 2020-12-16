package com.jasonless.mall.service.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Jasonless
 * @date 2020/12/14
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.jasonless.mall.api.goods.feign","com.jasonless.mall.api.cart.feign"})
public class MallOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallOrderApplication.class,args);
    }

}
