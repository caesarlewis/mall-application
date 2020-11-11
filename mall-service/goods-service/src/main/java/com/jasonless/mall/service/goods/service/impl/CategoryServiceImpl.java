package com.jasonless.mall.service.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasonless.mall.api.goods.entity.Category;
import com.jasonless.mall.service.goods.mapper.CategoryMapper;
import com.jasonless.mall.service.goods.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jasonless
 * @date 2020/11/11
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findByParentId(Integer pid) {
        //条件封装对象
        QueryWrapper<Category> queryWrapper = new QueryWrapper<Category>();
        queryWrapper.eq("parent_id",pid);
        return categoryMapper.selectList(queryWrapper);
    }
}
