package com.jasonless.mall.service.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Jasonless
 * @date 2020/12/11
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableFeignClients(basePackages = "com.jasonless.mall.api.goods.feign")
public class MallCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallCartApplication.class,args);
    }

}
