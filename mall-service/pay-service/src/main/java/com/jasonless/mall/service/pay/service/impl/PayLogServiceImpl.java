package com.jasonless.mall.service.pay.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasonless.mall.api.pay.model.PayLog;
import com.jasonless.mall.service.pay.dao.PayLogMapper;
import com.jasonless.mall.service.pay.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jasonless
 * @date 2020/12/16
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

    @Autowired
    private PayLogMapper payLogMapper;

    /***
     * 记录日志
     * @param payLog
     */
    @Override
    public void log(PayLog payLog) {
        //本地操作
        int count = payLogMapper.insert(payLog);
    }
}
