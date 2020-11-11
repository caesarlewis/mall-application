package com.jasonless.mall.service.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasonless.mall.api.goods.entity.Brand;
import com.jasonless.mall.service.goods.mapper.BrandMapper;
import com.jasonless.mall.service.goods.service.BrandService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jasonless
 * @date 2020/10/26
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {



    @Override
    public List<Brand> queryList(Brand brand) {
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<Brand>();
        if(!StringUtils.isEmpty(brand.getName())){
            queryWrapper.like("name",brand.getName());
        }
        if(!StringUtils.isEmpty(brand.getInitial())){
            queryWrapper.eq("initial",brand.getInitial());
        }

        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public Page<Brand> queryPageList(Long currentPage, Long size, Brand brand) {
        // 封装查询条件
        Page<Brand> page = this.baseMapper.selectPage(
                new Page<Brand>(currentPage, size),
                new QueryWrapper<Brand>()
                        .like("name", brand.getName()));
        return page;
    }
}
