package com.jasonless.mall.service.canal.listener;

import com.alibaba.fastjson.JSON;
import com.jasonless.mall.api.goods.entity.Sku;
import com.jasonless.mall.api.search.feign.SkuSearchFeign;
import com.jasonless.mall.api.search.model.SkuEs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

/**
 * @author Jasonless
 * @date 2020/12/7
 */
@CanalTable(value = "sku")
@Component
public class SkuHandler implements EntryHandler<Sku> {

    @Autowired
    private SkuSearchFeign skuSearchFeign;


    /***
     * 增加数据监听
     * @param sku
     */
    @Override
    public void insert(Sku sku) {
        if(sku.getStatus().intValue()==1){
            //将Sku转成JSON，再将JSON转成SkuEs
            skuSearchFeign.add(JSON.parseObject(JSON.toJSONString(sku), SkuEs.class));
        }
    }

    /****
     * 修改数据监听
     * @param before
     * @param after
     */
    @Override
    public void update(Sku before, Sku after) {
        if(after.getStatus().intValue()==2){
            //删除索引
            skuSearchFeign.del(after.getId());
        }else{
            //更新
            skuSearchFeign.add(JSON.parseObject(JSON.toJSONString(after), SkuEs.class));
        }
    }

    /***
     * 删除数据监听
     * @param sku
     */
    @Override
    public void delete(Sku sku) {
        skuSearchFeign.del(sku.getId());
    }


}
