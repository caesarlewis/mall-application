package com.jasonless.mall.service.search.mapper;

import com.jasonless.mall.api.search.model.SeckillGoodsEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author LiuShiZeng
 * 2021/4/10
 * 写注释吧
 */
public interface SeckillGoodsSearchMapper extends ElasticsearchRepository<SeckillGoodsEs,String> {

    //根据ActivityId搜索数据
    List<SeckillGoodsEs> searchByActivityId(String acid);

}
