package com.jasonless.mall.api.page.feign;

import com.jasonless.mall.common.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author LiuShiZeng
 * 2021/4/10
 * 写注释吧
 */
@FeignClient(value = "mall-web-page")
public interface SeckillPageFeign {

    /***
     * 秒杀详情页生成
     * @param id
     * @return
     */
    @GetMapping(value = "/page/seckill/goods/{id}")
    ResponseResult page(@PathVariable(value = "id")String id) throws Exception;

    /***
     * 删除指定活动的页面
     */
    @DeleteMapping(value = "/page/seckill/goods/{acid}")
    ResponseResult deleByAct(@PathVariable("acid")String acid);

}
