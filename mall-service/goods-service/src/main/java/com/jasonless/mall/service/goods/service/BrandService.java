package com.jasonless.mall.service.goods.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jasonless.mall.api.goods.entity.Brand;

import java.util.List;

/**
 * @author Jasonless
 * @date 2020/10/26
 */
public interface BrandService extends IService<Brand> {

    List<Brand> queryList(Brand brand);

    Page<Brand> queryPageList(Long currentPage,Long size,Brand brand );

}
