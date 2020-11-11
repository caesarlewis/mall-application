package com.jasonless.mall.service.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jasonless.mall.api.goods.entity.Category;

import java.util.List;

/**
 * @author Jasonless
 * @date 2020/11/11
 */
public interface CategoryService extends IService<Category> {

    /***
     * 根据分类父ID查询所有子类
     */
    List<Category> findByParentId(Integer pid);

}
