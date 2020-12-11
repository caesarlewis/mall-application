package com.jasonless.mall.search.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Jasonless
 * @date 2020/12/9
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.jasonless.mall.api.search.feign"})
public class WebSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSearchApplication.class,args);
    }

}
