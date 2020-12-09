package com.jasonless.mall.service.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author Jasonless
 * @date 2020/12/7
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableElasticsearchRepositories(basePackages = {"com.jasonless.mall.service.search.mapper"})
public class MallSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallSearchApplication.class,args);
    }
}
