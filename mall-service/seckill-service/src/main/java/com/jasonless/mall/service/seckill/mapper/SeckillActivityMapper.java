package com.jasonless.mall.service.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jasonless.mall.api.seckill.model.SeckillActivity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LiuShiZeng
 */
@Mapper
@Repository
public interface SeckillActivityMapper extends BaseMapper<SeckillActivity> {

    /**
     * 有效活动查询
     * @return
     */
    @Select("SELECT * FROM seckill_activity WHERE end_time>NOW() ORDER BY start_time ASC LIMIT 5")
    List<SeckillActivity> validActivity();

}
