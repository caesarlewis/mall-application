package com.jasonless.mall.service.seckill.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jasonless.mall.api.seckill.model.SeckillGoods;
import com.jasonless.mall.service.seckill.mapper.SeckillGoodsMapper;
import com.jasonless.mall.service.seckill.service.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeckillGoodsServiceImpl extends ServiceImpl<SeckillGoodsMapper, SeckillGoods> implements SeckillGoodsService {

    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;


    @Override
    public List<SeckillGoods> actGoods(String acid) {
        QueryWrapper<SeckillGoods> seckillGoodsQueryWrapper = new QueryWrapper<SeckillGoods>();
        seckillGoodsQueryWrapper.eq("activity_id",acid);
        return seckillGoodsMapper.selectList(seckillGoodsQueryWrapper);
    }
}
