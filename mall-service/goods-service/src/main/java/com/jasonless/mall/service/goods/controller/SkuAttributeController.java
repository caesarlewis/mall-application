package com.jasonless.mall.service.goods.controller;

import com.jasonless.mall.api.goods.model.SkuAttribute;
import com.jasonless.mall.common.util.ResponseResult;
import com.jasonless.mall.service.goods.service.SkuAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jasonless
 * @date 2020/11/11
 */
@RestController
@RequestMapping(value = "/skuAttribute")
@CrossOrigin
public class SkuAttributeController {

    @Autowired
    private SkuAttributeService skuAttributeService;

    /*****
     * 根据分类ID查询属性集合
     * @return
     */
    @GetMapping(value = "/category/{id}")
    public ResponseResult categorySkuAttributeList(@PathVariable(value = "id")Integer id){
        List<SkuAttribute> skuAttributes = skuAttributeService.queryList(id);
        return ResponseResult.ok(skuAttributes);
    }

}
