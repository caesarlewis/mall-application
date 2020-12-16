package com.jasonless.mall.service.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jasonless.mall.api.goods.model.SkuAttribute;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jasonless
 * @date 2020/11/11
 */
@Repository
@Mapper
public interface SkuAttributeMapper extends BaseMapper<SkuAttribute> {

    /****
     * 1、根据分类ID查询属性ID集合
     * 2、根据属性ID集合查询属性集合
     */
    @Select("select * from sku_attribute where id IN(SELECT attr_id FROM category_attr WHERE category_id=#{id})")
    List<SkuAttribute> queryByCategoryId(Integer id);

}
