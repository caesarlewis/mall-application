package com.jasonless.mall.service.canal.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author LiuShiZeng
 * 2021/4/10
 * 写注释吧
 */
@Component
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext act;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        act = applicationContext;
    }

    public static <T>T getBean(Class clazz){
        return act.getBean((Class<T>) clazz);
    }
}
