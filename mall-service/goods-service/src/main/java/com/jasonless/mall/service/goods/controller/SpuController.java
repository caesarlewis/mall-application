package com.jasonless.mall.service.goods.controller;

import com.jasonless.mall.api.goods.entity.Product;
import com.jasonless.mall.common.util.ResponseResult;
import com.jasonless.mall.service.goods.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jasonless
 * @date 2020/11/11
 */
@RestController
@RequestMapping(value = "/spu")
@CrossOrigin
public class SpuController {

    @Autowired
    private SpuService spuService;

    /*****
     * 产品保存
     */
    @PostMapping(value = "/save")
    public ResponseResult save(@RequestBody Product product){
        spuService.saveProduct(product);
        return ResponseResult.ok();
    }

    /***
     * 根据ID查询
     */
    @GetMapping(value = "/product/{id}")
    public ResponseResult<Product> one(@PathVariable(value = "id")String id){
        Product product = spuService.findBySpuId(id);
        return ResponseResult.ok(product);
    }

}
