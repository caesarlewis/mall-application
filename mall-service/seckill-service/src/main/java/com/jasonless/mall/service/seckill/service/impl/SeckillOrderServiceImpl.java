package com.jasonless.mall.service.seckill.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jasonless.mall.api.seckill.model.SeckillOrder;
import com.jasonless.mall.service.seckill.mapper.SeckillOrderMapper;
import com.jasonless.mall.service.seckill.service.SeckillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeckillOrderServiceImpl extends ServiceImpl<SeckillOrderMapper, SeckillOrder> implements SeckillOrderService {

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;


}
