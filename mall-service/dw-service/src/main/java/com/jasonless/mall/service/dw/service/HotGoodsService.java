package com.jasonless.mall.service.dw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jasonless.mall.api.dw.model.HotGoods;
import com.jasonless.mall.service.dw.util.DruidPage;

import java.util.List;
import java.util.Map;

/**
 * @author LiuShiZeng
 * 2021/4/11
 * 写注释吧
 */
public interface HotGoodsService extends IService<HotGoods> {

    List<HotGoods> topNum(Integer size);

    DruidPage<List<HotGoods>> pageList(Integer page, Integer size);


    DruidPage<List<HotGoods>> pageListSort(Integer page, Integer size, String sort, String sortType);


    List<HotGoods> search(Integer size, Integer hour);

    List<HotGoods> search(Integer size, Integer hour, String[] ids);

    List<Map<String,String>> searchHotGoods(Integer size, Integer hour, String[] urls, Integer max);

}
