package com.jasonless.mall.service.seckill.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jasonless.mall.api.seckill.model.SeckillGoods;
import com.jasonless.mall.service.seckill.mapper.SeckillGoodsMapper;
import com.jasonless.mall.service.seckill.service.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeckillGoodsServiceImpl extends ServiceImpl<SeckillGoodsMapper, SeckillGoods> implements SeckillGoodsService {

    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public List<SeckillGoods> actGoods(String acid) {
        QueryWrapper<SeckillGoods> seckillGoodsQueryWrapper = new QueryWrapper<SeckillGoods>();
        seckillGoodsQueryWrapper.eq("activity_id",acid);
        return seckillGoodsMapper.selectList(seckillGoodsQueryWrapper);
    }


    /***
     * 隔离
     * @param uri(id)
     */
    @Override
    public void isolation(String uri) {
        //锁定
        QueryWrapper<SeckillGoods> seckillGoodsQueryWrapper = new QueryWrapper<SeckillGoods>();
        seckillGoodsQueryWrapper.eq("islock",0);
        seckillGoodsQueryWrapper.eq("id",uri);
        seckillGoodsQueryWrapper.gt("store_count",0);
        SeckillGoods seckillGoods = new SeckillGoods();
        seckillGoods.setIslock(1);
        int update = seckillGoodsMapper.update(seckillGoods, seckillGoodsQueryWrapper);
        if(update>0){
            //数据存入缓存隔离(需要控制集群环境问题，所以定时任务分片只设置成1个分片)
            seckillGoods = seckillGoodsMapper.selectById(uri);
            redisTemplate.boundHashOps("HotSeckillGoods").increment(uri,seckillGoods.getStoreCount());
        }
    }
}
