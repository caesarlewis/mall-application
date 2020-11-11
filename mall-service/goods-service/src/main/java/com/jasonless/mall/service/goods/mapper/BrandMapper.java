package com.jasonless.mall.service.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jasonless.mall.api.goods.entity.Brand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jasonless
 * @date 2020/10/26
 */
@Mapper
@Repository
public interface BrandMapper extends BaseMapper<Brand> {

    /****
     * 1、根据分类ID查询品牌ID集合
     */
    @Select("SELECT brand_id FROM category_brand WHERE category_id=#{id}")
    List<Integer> queryBrandIds(Integer id);

}
