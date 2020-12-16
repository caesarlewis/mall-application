package com.jasonless.mall.service.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jasonless.mall.api.goods.model.Spu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Jasonless
 * @date 2020/11/11
 */
@Repository
@Mapper
public interface SpuMapper extends BaseMapper<Spu> {
}
