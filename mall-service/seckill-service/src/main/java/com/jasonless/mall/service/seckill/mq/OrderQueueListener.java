package com.jasonless.mall.service.seckill.mq;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author LiuShiZeng
 * 2021/4/11
 * 写注释吧
 */
@RocketMQMessageListener(
        topic = "order-queue", 					//topic：和消费者发送的topic相同
        consumerGroup = "orderqueue-consumer",     //group：不用和生产者group相同
        selectorExpression = "*") 			    //tag
@Component
public class OrderQueueListener implements RocketMQListener {

    /***
     * 排队信息
     * @param message
     */
    @Override
    public void onMessage(Object message) {
        System.out.println("排队信息："+message);
    }
}
