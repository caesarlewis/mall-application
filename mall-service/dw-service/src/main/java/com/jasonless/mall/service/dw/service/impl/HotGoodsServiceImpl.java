package com.jasonless.mall.service.dw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasonless.mall.api.dw.model.HotGoods;
import com.jasonless.mall.service.dw.mapper.HotGoodsMapper;
import com.jasonless.mall.service.dw.service.HotGoodsService;
import com.jasonless.mall.service.dw.util.DruidPage;
import com.jasonless.mall.service.dw.util.TimeUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author LiuShiZeng
 * 2021/4/11
 * 写注释吧
 */
@Service
public class HotGoodsServiceImpl extends ServiceImpl<HotGoodsMapper, HotGoods> implements HotGoodsService {

    @Autowired
    private HotGoodsMapper hotGoodsMapper;

    /***
     * 查询前N条记录
     * @param size
     * @return
     */
    @Override
    public List<HotGoods> topNum(Integer size) {
        return hotGoodsMapper.topNum(size);
    }


    //分页查询
    @Override
    public DruidPage<List<HotGoods>> pageList(Integer page, Integer size) {
        //创建分页
        DruidPage<List<HotGoods>> pageInfo = new DruidPage<List<HotGoods>>(page,size);
        //总记录数查询
        Integer total = hotGoodsMapper.selectCount(null);
        //集合查询
        List<HotGoods> hotGoods = hotGoodsMapper.pageList(pageInfo);
        return pageInfo.pages(hotGoods,total);
    }


    /***
     * 分页+排序
     * @param page
     * @param size
     * @param sort
     * @param sortType
     * @return
     */
    @Override
    public DruidPage<List<HotGoods>> pageListSort(Integer page, Integer size, String sort, String sortType) {
        //创建分页
        DruidPage<List<HotGoods>> pageInfo = new DruidPage<List<HotGoods>>(page,size,sort,sortType);
        //总记录数查询
        Integer total = hotGoodsMapper.selectCount(null);
        //集合查询
        List<HotGoods> hotGoods = hotGoodsMapper.pageListSort(pageInfo);
        return pageInfo.pages(hotGoods,total);
    }

    /***
     * 查询历史数据
     * @param size
     * @param hour
     * @return
     */
    @Override
    public List<HotGoods> search(Integer size, Integer hour) {
        return hotGoodsMapper.search(size, TimeUtil.beforeTime(TimeUtil.UNIT_HOUR,hour));
    }

    /***
     * 排除指定uri数据
     * @param size
     * @param hour
     * @param urls
     * @return
     */
    @Override
    public List<HotGoods> search(Integer size, Integer hour, String[] urls) {
        String urlsJoin = StringUtils.join(urls, "','");
        return hotGoodsMapper.searchExclude(size, TimeUtil.beforeTime(TimeUtil.UNIT_HOUR,hour),urlsJoin);
    }


    /***
     * 热门商品查询
     * @param size：TopN
     * @param hour：N小时前数据统计
     * @param urls：排除之前判断的热点商品
     * @param max：访问频率超过max作为统计条件
     * @return
     */
    @Override
    public List<Map<String,String>> searchHotGoods(Integer size, Integer hour, String[] urls, Integer max) {
        String urlsJoin = StringUtils.join(urls, "','");
        return hotGoodsMapper.searchHotGoods(size, TimeUtil.beforeTime(TimeUtil.UNIT_HOUR,hour),urlsJoin,max);
    }



}
