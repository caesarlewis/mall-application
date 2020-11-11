package com.jasonless.mall.service.goods.controller;

import com.jasonless.mall.api.goods.entity.Category;
import com.jasonless.mall.common.util.ResponseResult;
import com.jasonless.mall.service.goods.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jasonless
 * @date 2020/11/11
 */
@RestController
@RequestMapping(value = "/category")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /****
     * 根据分类父ID查询子分类
     */
    @GetMapping(value = "/parent/{id}")
    public ResponseResult<List<Category>> findByParentId(@PathVariable("id")Integer id){
        return ResponseResult.ok(categoryService.findByParentId(id));
    }

}
