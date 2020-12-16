package com.jasonless.mall.service.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jasonless.mall.api.goods.model.SkuAttribute;

import java.util.List;

/**
 * @author Jasonless
 * @date 2020/11/11
 */
public interface SkuAttributeService extends IService<SkuAttribute> {

    /***
     * 根据分类ID查询属性加集合
     */
    List<SkuAttribute> queryList(Integer id);

}
