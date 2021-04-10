package com.jasonless.mall.service.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jasonless.mall.api.seckill.model.SeckillGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


/**
 * @author LiuShiZeng
 */
@Mapper
@Repository
public interface SeckillGoodsMapper extends BaseMapper<SeckillGoods> {

    
}
