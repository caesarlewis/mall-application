package com.jasonless.mall.service.seckill.controller;


import com.jasonless.mall.api.seckill.model.SeckillActivity;
import com.jasonless.mall.common.util.ResponseResult;
import com.jasonless.mall.service.seckill.service.SeckillActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "activity")
public class SeckillActivityController {

    @Autowired
    private SeckillActivityService seckillActivityService;

    /***
     * 未过期的活动列表
     */
    @GetMapping
    public ResponseResult<List<SeckillActivity>> list(){
        //有效的活动时间查询
        List<SeckillActivity> list = seckillActivityService.validActivity();
        return ResponseResult.ok(list);
    }

}
