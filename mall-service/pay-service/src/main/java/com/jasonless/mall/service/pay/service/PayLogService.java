package com.jasonless.mall.service.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jasonless.mall.api.pay.model.PayLog;

/**
 * @author Jasonless
 * @date 2020/12/16
 */
public interface PayLogService extends IService<PayLog> {
    void log(PayLog payLog);
}
