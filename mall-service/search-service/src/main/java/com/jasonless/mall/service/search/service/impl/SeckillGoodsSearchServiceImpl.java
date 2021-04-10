package com.jasonless.mall.service.search.service.impl;

import com.jasonless.mall.api.search.model.SeckillGoodsEs;
import com.jasonless.mall.service.search.mapper.SeckillGoodsSearchMapper;
import com.jasonless.mall.service.search.service.SeckillGoodsSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiuShiZeng
 * 2021/4/10
 * 写注释吧
 */
@Service
public class SeckillGoodsSearchServiceImpl implements SeckillGoodsSearchService {

    @Autowired
    private SeckillGoodsSearchMapper seckillGoodsSearchMapper;

    /***
     * 导入数据到ES中
     * @param seckillGoodsEs
     */
    @Override
    public void add(SeckillGoodsEs seckillGoodsEs) {
        seckillGoodsSearchMapper.save(seckillGoodsEs);
    }

    /***
     * 根据活动ID搜索
     * @param acid
     * @return
     */
    @Override
    public List<SeckillGoodsEs> search(String acid) {
        return seckillGoodsSearchMapper.searchByActivityId(acid);
    }

}

