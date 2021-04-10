package com.jasonless.mall.service.canal.task.statictask;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.elasticjob.lite.annotation.ElasticSimpleJob;
import org.springframework.stereotype.Component;

/**
 * @author LiuShiZeng
 * 2021/4/10
 * 写注释吧
 */
@ElasticSimpleJob(
        cron = "0/10 * * * * ?",
        jobName = "synctask",
        shardingTotalCount = 1
)
@Component
public class SyncStaticTask implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println("执行任务。。。");
    }
}
