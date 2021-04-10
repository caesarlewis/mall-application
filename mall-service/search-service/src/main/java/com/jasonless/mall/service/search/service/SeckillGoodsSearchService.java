package com.jasonless.mall.service.search.service;

import com.jasonless.mall.api.search.model.SeckillGoodsEs;

import java.util.List;

/**
 * @author LiuShiZeng
 * 2021/4/10
 * 写注释吧
 */
public interface SeckillGoodsSearchService {

    /***
     * 导入数据到ES中
     * @param seckillGoodsEs
     */
    void add(SeckillGoodsEs seckillGoodsEs);

    /**
     * 根据活动ID搜索
     * @param acid
     * @return
     */
    List<SeckillGoodsEs> search(String acid);

}
