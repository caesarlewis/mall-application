package com.jasonless.mall.service.seckill.controller;


import com.jasonless.mall.api.seckill.model.SeckillGoods;
import com.jasonless.mall.common.util.ResponseResult;
import com.jasonless.mall.service.seckill.service.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/seckill/goods")
public class SeckillGoodsController {

    @Autowired
    private SeckillGoodsService seckillGoodsService;

    /***
     * 根据活动查询秒杀商品集合
     * @param acid
     * @return
     */
    @GetMapping(value = "/act/{acid}")
    public ResponseResult<List<SeckillGoods>> actGoods(@PathVariable("acid") String acid){
        List<SeckillGoods> seckillGoods = seckillGoodsService.actGoods(acid);
        return ResponseResult.ok(seckillGoods);
    }
}
