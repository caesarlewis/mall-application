package com.jasonless.mall.service.pay.mq;

import com.jasonless.mall.common.util.Signature;
import com.jasonless.mall.service.pay.service.WeixinPayService;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author LiuShiZeng
 * 2021/4/10
 * 写注释吧
 */
@Component
@RocketMQMessageListener(topic = "refund", consumerGroup = "orderrefund-group")
public class RefundResultListener implements RocketMQListener, RocketMQPushConsumerLifecycleListener {

    @Autowired
    private WeixinPayService weixinPayService;

    @Autowired
    private Signature signature;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /***
     * 监听消息
     * 实现RocketMQPushConsumerLifecycleListener监听器之后，此方法不调用
     * @param message
     */
    @Override
    public void onMessage(Object message) {
    }

    /***
     * 消息监听：退款事务监听
     * @param consumer
     */
    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                try {
                    for (MessageExt msg : msgs) {
                        String result = new String(msg.getBody(),"UTF-8");

                        //数据解析，并验签校验
                        Map<String, String> map = signature.security(result);
                        if(map!=null){
                            //执行退款申请
                            Map<String, String>  resultMap = weixinPayService.refund(map);
                            //执行事务通知
                            Message message = MessageBuilder.withPayload(resultMap).build();
                            rocketMQTemplate.sendMessageInTransaction("refundstatustx",
                                    "refundstatus",
                                    message,
                                    resultMap);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
                //消费状态
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
    }
}
