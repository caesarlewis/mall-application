package com.jasonless.mall.service.order.mq;

import com.jasonless.mall.order.api.model.OrderRefund;
import com.jasonless.mall.service.order.service.OrderService;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author LiuShiZeng
 * 2021/4/10
 * 写注释吧
 */
@Component
@RocketMQTransactionListener(txProducerGroup = "refundtx")
public class RefundTransactionListener implements RocketMQLocalTransactionListener {

    @Autowired
    private OrderService orderService;

    /***
     * 发送prepare消息成功后回调该方法用于执行本地事务
     * @param message:回传的消息，利用transactionId即可获取到该消息的唯一Id
     * @param o:调用send方法时传递的参数，当send时候若有额外的参数可以传递到send方法中，这里能获取到
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        try {
            //================本地事务操作开始=====================================
            //修改本地状态
            int count = orderService.refund((OrderRefund) o);

            //如果申请退款失败，则回滚half消息
            if(count<=0){
                return RocketMQLocalTransactionState.ROLLBACK;
            }
            //================本地事务操作结束=====================================
        } catch (Exception e) {
            //异常,消息回滚
            e.printStackTrace();
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        return RocketMQLocalTransactionState.UNKNOWN;
    }

    /***
     * 消息回查
     * @param message
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        return RocketMQLocalTransactionState.COMMIT;
    }
}
