package com.jasonless.mall.service.goods.controller;

import com.jasonless.mall.api.cart.model.Cart;
import com.jasonless.mall.api.goods.model.Sku;
import com.jasonless.mall.common.util.ResponseResult;
import com.jasonless.mall.service.goods.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jasonless
 * @date 2020/12/3
 */
@RestController
@RequestMapping(value = "/sku")
@CrossOrigin
public class SkuController {

    @Autowired
    private SkuService skuService;

    /**
     * 指定分类下的推广产品列表
     * @return
     */
    @GetMapping(value = "/aditems/type/{id}")
    public List<Sku> typeItems(@PathVariable(value = "id")Integer id){
        List<Sku> adSkuItems = skuService.typeSkuItems(id);
        return adSkuItems;
    }

    /****
     * 根据ID获取Sku
     */
    @GetMapping(value = "/{id}")
    public ResponseResult<Sku> one (@PathVariable(value = "id") String id){
        Sku sku = skuService.getById(id);
        return ResponseResult.ok(sku);
    }

    /***
     * 库存递减
     * @param carts
     * @return
     */
    @PostMapping(value = "/dcount")
    public ResponseResult decount(@RequestBody List<Cart> carts){
        skuService.decount(carts);
        return ResponseResult.ok();
    }

}
