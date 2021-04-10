package com.jasonless.mall.service.search.controller;

import com.jasonless.mall.api.search.model.SeckillGoodsEs;
import com.jasonless.mall.common.util.ResponseResult;
import com.jasonless.mall.service.search.service.SeckillGoodsSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LiuShiZeng
 * 2021/4/10
 * 写注释吧
 */
@RestController
@RequestMapping(value = "/seckill/goods")
public class SeckillGoodsSearchController {

    @Autowired
    private SeckillGoodsSearchService seckillGoodsSearchService;

    /***
     * 导入数据到索引库
     */
    @PostMapping(value = "/add")
    public ResponseResult add(@RequestBody SeckillGoodsEs seckillGoodsEs){
        seckillGoodsSearchService.add(seckillGoodsEs);
        return ResponseResult.ok();
    }


    /****
     * 搜索商品数据
     */
    @GetMapping(value = "/search")
    public ResponseResult<List<SeckillGoodsEs>> list(@RequestParam("acid")String acid){
        //根据活动ID搜索
        List<SeckillGoodsEs> seckillGoodsEsList =  seckillGoodsSearchService.search(acid);
        return ResponseResult.ok(seckillGoodsEsList);
    }

}
