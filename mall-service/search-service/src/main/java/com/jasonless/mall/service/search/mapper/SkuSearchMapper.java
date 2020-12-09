package com.jasonless.mall.service.search.mapper;

import com.jasonless.mall.api.search.model.SkuEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Jasonless
 * @date 2020/12/7
 */
public interface SkuSearchMapper extends ElasticsearchRepository<SkuEs,String> {
}
