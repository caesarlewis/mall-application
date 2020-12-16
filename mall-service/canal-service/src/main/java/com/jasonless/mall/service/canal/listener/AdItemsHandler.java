package com.jasonless.mall.service.canal.listener;

import com.jasonless.mall.api.goods.model.AdItems;
import com.jasonless.mall.api.goods.feign.SkuFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

/**
 * @author Jasonless
 * @date 2020/12/7
 */
@CanalTable(value = "ad_items")
@Component
public class AdItemsHandler implements EntryHandler<AdItems> {

    @Autowired
    private SkuFeign skuFeign;

    /****
     * 数据库增加数据，执行该方法
     * @param adItems
     */
    @Override
    public void insert(AdItems adItems) {
        //重新加载缓存
        skuFeign.updateTypeItems(adItems.getType());
    }

    /****
     * 数据库修改数据，执行该方法
     */
    @Override
    public void update(AdItems before, AdItems after) {
        if(before.getType().intValue()!=after.getType().intValue()){
            //重新加载变更前分类的ID对应的推广产品
            skuFeign.updateTypeItems(before.getType());
        }

        //重新加载缓存
        skuFeign.updateTypeItems(after.getType());
    }

    /****
     * 数据库删除数据，执行该方法
     */
    @Override
    public void delete(AdItems adItems) {
        skuFeign.deleteTypeItems(adItems.getType());
    }
}
