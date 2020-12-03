package com.jasonless.mall.service.goods.controller;

import com.jasonless.mall.api.goods.entity.Sku;
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

}
