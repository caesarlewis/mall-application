package com.jasonless.mall.service.goods.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jasonless.mall.api.goods.model.Brand;
import com.jasonless.mall.common.util.ResponseResult;
import com.jasonless.mall.service.goods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jasonless
 * @date 2020/10/26
 */
@RestController
@RequestMapping(value = "/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 增加品牌
     * @param brand
     * @return
     */
    public ResponseResult add(@RequestBody Brand brand){
        brandService.save(brand);
        return ResponseResult.ok();
    }

    @PutMapping
    public ResponseResult update(@RequestBody Brand brand){
        brandService.updateById(brand);
        return ResponseResult.ok();
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable(value = "id") Integer id){
        brandService.removeById(id);
        return ResponseResult.ok();
    }

    @PostMapping(value = "/list")
    public ResponseResult list(@RequestBody(required = false) Brand brand ){
        List<Brand> brands = brandService.queryList(brand);
        return ResponseResult.ok(brands);
    }

    @PostMapping(value = "/list/{page}/{size}")
    public ResponseResult list(@PathVariable(value = "page") Long currentPage,
                               @PathVariable(value = "size") Long size,
                               @RequestBody(required = false) Brand brand){
        Page<Brand> brandPage = brandService.queryPageList(currentPage,size,brand);
        return ResponseResult.ok(brandPage);
    }

    /****
     * 根据分类ID查询品牌集合
     */
    @GetMapping(value = "/category/{pid}")
    public ResponseResult  categoryBrands(@PathVariable(value = "pid")Integer pid){
        List<Brand> brands = brandService.queryByCategoryId(pid);
        return ResponseResult.ok(brands);
    }


}
