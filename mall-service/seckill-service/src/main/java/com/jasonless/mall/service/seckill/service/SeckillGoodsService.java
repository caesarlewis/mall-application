package com.jasonless.mall.service.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jasonless.mall.api.seckill.model.SeckillGoods;

import java.util.List;


public interface SeckillGoodsService extends IService<SeckillGoods> {

    //根据活动ID查询商品信息
    List<SeckillGoods> actGoods(String acid);

    /***
     * 隔离
     * @param uri
     */
    void isolation(String uri);

}
