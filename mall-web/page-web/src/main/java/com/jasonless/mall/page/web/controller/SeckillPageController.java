package com.jasonless.mall.page.web.controller;

import com.jasonless.mall.api.seckill.feign.SeckillGoodsFeign;
import com.jasonless.mall.api.seckill.model.SeckillGoods;
import com.jasonless.mall.common.util.ResponseResult;
import com.jasonless.mall.page.web.service.SeckillPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LiuShiZeng
 * 2021/4/10
 * 写注释吧
 */
@RestController
@RequestMapping(value = "/page")
public class SeckillPageController {

    @Autowired
    private SeckillPageService seckillPageService;

    @Autowired
    private SeckillGoodsFeign seckillGoodsFeign;


    /***
     * 删除指定活动的页面
     */
    @DeleteMapping(value = "/seckill/goods/{acid}")
    public ResponseResult deleByAct(@PathVariable("acid")String acid){
        //1.查询当前活动ID对应的商品列表数据\
        ResponseResult<List<SeckillGoods>> listRespResult = seckillGoodsFeign.actGoods(acid);
        List<SeckillGoods> goodsList = listRespResult.getData();
        //2.根据列表数据删除页面
        if(goodsList!=null){
            //循环删除页面
            for (SeckillGoods seckillGoods : goodsList) {
                seckillPageService.delete(seckillGoods.getId());
            }
        }
        return ResponseResult.ok();
    }

    /***
     * 秒杀详情页生成
     * @param id
     * @return
     */
    @GetMapping(value = "/seckill/goods/{id}")
    public ResponseResult page(@PathVariable(value = "id")String id) throws Exception {
        //生成静态页
        seckillPageService.html(id);
        return ResponseResult.ok();
    }
}
