package com.jasonless.mall.service.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jasonless.mall.api.goods.model.AdItems;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Jasonless
 * @date 2020/12/3
 */
@Mapper
@Repository
public interface AdItemsMapper extends BaseMapper<AdItems> {



}
