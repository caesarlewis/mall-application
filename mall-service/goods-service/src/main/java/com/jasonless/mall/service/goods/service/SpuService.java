package com.jasonless.mall.service.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jasonless.mall.api.goods.model.Product;
import com.jasonless.mall.api.goods.model.Spu;

/**
 * @author Jasonless
 * @date 2020/11/11
 */
public interface SpuService extends IService<Spu> {

    /****
     * 产品保存
     */
    void saveProduct(Product product);

    //查询商品详情
    Product findBySpuId(String id);

}
