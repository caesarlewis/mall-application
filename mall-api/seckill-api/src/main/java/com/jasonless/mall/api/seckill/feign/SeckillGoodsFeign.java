package com.jasonless.mall.api.seckill.feign;

import com.jasonless.mall.api.seckill.model.SeckillGoods;
import com.jasonless.mall.common.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author LiuShiZeng
 * 2021/4/10
 * 写注释吧
 */
@FeignClient(value = "mall-seckill")
public interface SeckillGoodsFeign {

    /***
     * 根据活动查询秒杀商品集合
     * @param acid
     * @return
     */
    @GetMapping(value = "/seckill/goods/act/{acid}")
    ResponseResult<List<SeckillGoods>> actGoods(@PathVariable("acid") String acid);

    /***
     * 根据ID查询秒杀商品详情
     * @param id
     * @return
     */
    @GetMapping(value = "/seckill/goods/{id}")
    ResponseResult<SeckillGoods> one(@PathVariable("id") String id);

}
