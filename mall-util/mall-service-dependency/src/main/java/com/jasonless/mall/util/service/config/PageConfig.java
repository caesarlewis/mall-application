package com.jasonless.mall.util.service.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jasonless
 * @date 2020/11/1
 */
@Configuration
public class PageConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        //设置数据类型
        paginationInterceptor.setDbType(DbType.MYSQL);
        return paginationInterceptor;
    }

}
