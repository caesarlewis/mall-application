package com.jasonless.mall.api.search.feign;

import com.jasonless.mall.api.search.model.SeckillGoodsEs;
import com.jasonless.mall.common.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author LiuShiZeng
 * 2021/4/10
 * 写注释吧
 */
@FeignClient(value = "mall-search")
public interface SeckillGoodsSearchFeign {

    /***
     * 导入数据到索引库
     */
    @PostMapping(value = "/seckill/goods/add")
    ResponseResult add(@RequestBody SeckillGoodsEs seckillGoodsEs);
}
