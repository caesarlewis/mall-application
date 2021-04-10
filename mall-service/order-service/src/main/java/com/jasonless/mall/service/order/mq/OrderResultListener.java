package com.jasonless.mall.service.order.mq;

import com.alibaba.fastjson.JSON;
import com.jasonless.mall.api.pay.model.PayLog;
import com.jasonless.mall.service.order.service.OrderService;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author Jasonless
 * @date 2020/12/16
 */
@Component
@RocketMQMessageListener(topic = "log", consumerGroup = "order-group")
public class OrderResultListener implements RocketMQListener, RocketMQPushConsumerLifecycleListener {

    @Autowired
    private OrderService orderService;

    /***
     * 监听消息
     * 实现RocketMQPushConsumerLifecycleListener监听器之后，此方法不调用
     * @param message
     */
    @Override
    public void onMessage(Object message) {

    }

    /***
     * 消息监听
     * @param defaultMQPushConsumer
     */
    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                try {
                    for (MessageExt msg : msgs) {
                        String result = new String(msg.getBody(),"UTF-8");
                        PayLog payLog = JSON.parseObject(result,PayLog.class);
                        if(payLog.getStatus().intValue()==2){
                            //支付成功
                            int count = orderService.updateAfterPayStatus(payLog.getPayId());
                            System.out.println(payLog.getId()+"================"+count);
                        }else{
                            //支付失败
                            //1：修改订单状态
                            //2：库存回滚
                        }
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                //消费状态
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
    }
}
