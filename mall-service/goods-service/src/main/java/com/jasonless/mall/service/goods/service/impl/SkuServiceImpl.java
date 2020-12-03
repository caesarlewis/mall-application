package com.jasonless.mall.service.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasonless.mall.api.goods.entity.AdItems;
import com.jasonless.mall.api.goods.entity.Sku;
import com.jasonless.mall.service.goods.mapper.AdItemsMapper;
import com.jasonless.mall.service.goods.mapper.SkuMapper;
import com.jasonless.mall.service.goods.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jasonless
 * @date 2020/12/3
 */
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private AdItemsMapper adItemsMapper;

    @Override
    public List<Sku> typeSkuItems(Integer id) {
        //查询所有分类下的推广
        QueryWrapper<AdItems> adItemsQueryWrapper = new QueryWrapper<>();
        adItemsQueryWrapper.eq("type",id);
        List<AdItems> adItems = adItemsMapper.selectList(adItemsQueryWrapper);
        //获取所有SKUID
        List<String> skuIds = adItems.stream().map(adItem -> adItem.getSkuId()).collect(Collectors.toList());

        //批量查询Sku
        List<Sku> skus = skuMapper.selectBatchIds(skuIds);
        return skus;
    }

    @Override
    public void delTypeSkuItems(Integer id) {

    }

    @Override
    public List<Sku> updateTypeSkuItems(Integer id) {
        return null;
    }
}
