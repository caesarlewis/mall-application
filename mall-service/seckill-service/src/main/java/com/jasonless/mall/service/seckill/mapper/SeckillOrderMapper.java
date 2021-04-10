package com.jasonless.mall.service.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jasonless.mall.api.seckill.model.SeckillOrder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * @author LiuShiZeng
 */
@Mapper
@Repository
public interface SeckillOrderMapper extends BaseMapper<SeckillOrder> {
}
