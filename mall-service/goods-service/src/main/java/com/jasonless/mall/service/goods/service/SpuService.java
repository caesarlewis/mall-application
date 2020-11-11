package com.jasonless.mall.service.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jasonless.mall.api.goods.entity.Product;
import com.jasonless.mall.api.goods.entity.Spu;

/**
 * @author Jasonless
 * @date 2020/11/11
 */
public interface SpuService extends IService<Spu> {

    /****
     * 产品保存
     */
    void saveProduct(Product product);

}