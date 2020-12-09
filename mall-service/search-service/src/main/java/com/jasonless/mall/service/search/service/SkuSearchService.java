package com.jasonless.mall.service.search.service;

import com.jasonless.mall.api.search.model.SkuEs;

import java.util.Map;

/**
 * @author Jasonless
 * @date 2020/12/7
 */
public interface SkuSearchService {

    Map<String,Object> search(Map<String,Object> searchMap);
    //增加索引
    void add(SkuEs skuEs);
    //删除索引
    void delete(String id);
}
