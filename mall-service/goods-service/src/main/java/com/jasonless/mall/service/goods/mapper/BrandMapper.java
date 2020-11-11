package com.jasonless.mall.service.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jasonless.mall.api.goods.entity.Brand;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Jasonless
 * @date 2020/10/26
 */
@Mapper
@Repository
public interface BrandMapper extends BaseMapper<Brand> {
}
