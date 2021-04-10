package com.jasonless.mall.service.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jasonless.mall.api.seckill.model.SeckillActivity;

import java.util.List;

public interface SeckillActivityService extends IService<SeckillActivity>{

    //有效活动时间查询
    List<SeckillActivity> validActivity();
}
