package com.jasonless.mall.service.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author Jasonless
 * @date 2020/11/11
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MallFileServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallFileServiceApplication.class,args);
    }

}
