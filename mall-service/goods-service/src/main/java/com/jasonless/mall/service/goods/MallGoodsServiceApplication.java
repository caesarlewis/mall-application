package com.jasonless.mall.service.goods;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author Jasonless
 * @date 2020/10/26
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.jasonless.mall.service.goods.mapper"})
//开启缓存
@EnableCaching
public class MallGoodsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallGoodsServiceApplication.class,args);
    }

}
