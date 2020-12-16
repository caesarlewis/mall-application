package com.jasonless.mall.service.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jasonless.mall.api.goods.model.Sku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author Jasonless
 * @date 2020/11/11
 */
@Repository
@Mapper
public interface SkuMapper extends BaseMapper<Sku> {

    //库存递减
    @Update("UPDATE sku SET num=num-#{num} WHERE id=#{id} AND num>=#{num}")
    int decount(@Param("id") String skuId,@Param("num") Integer num);

}
