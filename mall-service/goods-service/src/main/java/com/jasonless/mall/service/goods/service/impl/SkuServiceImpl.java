package com.jasonless.mall.service.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasonless.mall.api.cart.model.Cart;
import com.jasonless.mall.api.goods.model.AdItems;
import com.jasonless.mall.api.goods.model.Sku;
import com.jasonless.mall.service.goods.mapper.AdItemsMapper;
import com.jasonless.mall.service.goods.mapper.SkuMapper;
import com.jasonless.mall.service.goods.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jasonless
 * @date 2020/12/3
 */
@Service
@CacheConfig(cacheNames = "ad-items-skus")
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private AdItemsMapper adItemsMapper;

    /***
     * 根据推广产品分类ID查询指定分类下的产品列表
     * @param id
     * @return
     * ad-items-skus::1
     */
    @Cacheable(key ="#id" )
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

    /***
     * 根据分类id删除指定推广数据
     * @param id
     * @return
     */
    @CacheEvict(key ="#id" )
    @Override
    public void delTypeSkuItems(Integer id) {
        skuMapper.deleteById(id);
    }

    /****
     * 修改缓存
     * @param id
     * @return
     */
    @CachePut(key = "#id")
    @Override
    public List<Sku> updateTypeSkuItems(Integer id) {

        //1.查询当前分类下的所有列表信息
        QueryWrapper<AdItems> adItemsQueryWrapper = new QueryWrapper<AdItems>();
        adItemsQueryWrapper.eq("type",id);
        List<AdItems> adItems = adItemsMapper.selectList(adItemsQueryWrapper);

        //2.根据推广列表查询产品列表信息
        List<String> skuids = adItems.stream().map(adItem->adItem.getSkuId()).collect(Collectors.toList());
        return skuids==null || skuids.size()<=0? null : skuMapper.selectBatchIds(skuids);
    }

    /***
     * 库存递减
     * @param carts
     */
    @Override
    public void decount(List<Cart> carts) {
        for (Cart cart : carts) {
            //语句级控制，防止超卖
            int count = skuMapper.decount(cart.getSkuId(),cart.getNum());
            if(count<=0){
                throw new RuntimeException("库存不足！");
            }
        }
    }
}
