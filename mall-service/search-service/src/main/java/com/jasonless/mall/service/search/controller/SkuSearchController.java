package com.jasonless.mall.service.search.controller;

import com.jasonless.mall.api.search.model.SkuEs;
import com.jasonless.mall.common.util.ResponseResult;
import com.jasonless.mall.service.search.service.SkuSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Jasonless
 * @date 2020/12/7
 */
@RestController
@RequestMapping(value = "/search")
public class SkuSearchController {

    @Autowired
    private SkuSearchService skuSearchService;


    /***
     * 商品搜索
     */
    @GetMapping
    public ResponseResult<Map<String,Object>> search(@RequestParam(required = false)Map<String,Object> searchMap){
        Map<String, Object> resultMap = skuSearchService.search(searchMap);
        return ResponseResult.ok(resultMap);
    }

    /*****
     * 增加索引
     */
    @PostMapping(value = "/add")
    public ResponseResult add(@RequestBody SkuEs skuEs){
        skuSearchService.add(skuEs);
        return ResponseResult.ok();
    }

    /***
     * 删除索引
     */
    @DeleteMapping(value = "/del/{id}")
    public ResponseResult del(@PathVariable(value = "id")String id){
        skuSearchService.delete(id);
        return ResponseResult.ok();
    }

}
