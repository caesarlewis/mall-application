package com.jasonless.mall.service.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jasonless.mall.api.seckill.model.SeckillOrder;

import java.util.Map;


public interface SeckillOrderService extends IService<SeckillOrder> {

    /***
     * 热门商品抢单操作
     */
    int add(Map<String,Object> dataMap);
    
}
