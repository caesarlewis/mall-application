package com.jasonless.mall.service.canal.job.dynamic;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.jasonless.mall.api.page.feign.SeckillPageFeign;
import com.jasonless.mall.service.canal.spring.SpringContext;

/**
 * @author LiuShiZeng
 * 2021/4/10
 * 写注释吧
 */
public class DynamicJob implements SimpleJob {

    //执行的作业
    @Override
    public void execute(ShardingContext shardingContext) {
        //静态页删除
        delete(shardingContext.getJobParameter());
    }

    /***
     * 执行静态页删除
     */
    public void delete(String acid){
        //从容器中获取指定的实例
        SeckillPageFeign seckillPageFeign = SpringContext.getBean(SeckillPageFeign.class);
        seckillPageFeign.deleByAct(acid);
    }
}
