package com.jasonless.mall.service.dw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jasonless.mall.api.dw.model.HotGoods;
import com.jasonless.mall.service.dw.util.DruidPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author LiuShiZeng
 * 2021/4/11
 * 写注释吧
 */
@Repository
@Mapper
public interface HotGoodsMapper extends BaseMapper<HotGoods> {

    /***
     * 查询前N条记录
     * @param size
     * @return
     */
    @Select("select uri,__time as accesstime,ip from mslogs limit #{size}")
    List<HotGoods> topNum(Integer size);

    /**
     * 分页查询
     * @return
     */
    @Select("select uri,__time as accesstime,ip from mslogs limit #{size} offset #{offset}")
    List<HotGoods> pageList(DruidPage druidPage);

    /***
     * 排序+分页
     * @param pageInfo
     * @return
     */
    @Select("select uri,__time as accesstime,ip from mslogs order by ${sort} ${sortType} limit #{size} offset #{offset}")
    List<HotGoods> pageListSort(DruidPage<List<HotGoods>> pageInfo);


    /***
     * 搜索数据
     * @param size
     * @param time
     * @return
     */
    @Select("select uri,__time as accesstime,ip from mslogs where __time>=TIMESTAMP '${time}' limit #{size}")
    List<HotGoods> search(@Param("size") Integer size,@Param("time") String time);


    /***
     * 数据搜索
     * @param size
     * @param time
     * @param urls
     * @return
     */
    @Select("select uri,__time as accesstime,ip from mslogs where __time>=TIMESTAMP '${time}' and uri not in('${urls}') limit #{size}")
    List<HotGoods> searchExclude(@Param("size") Integer size,@Param("time") String time,@Param("urls")String urls);


    /****
     * 分组、聚合判断、TopN、时间判断、排序
     * @param size
     * @param time
     * @param urls
     * @param max
     * @return
     */
    @Select("SELECT uri,count(*) as viewCount FROM mslogs WHERE __time>=TIMESTAMP '${time}' AND uri NOT IN ('${urls}') GROUP BY uri HAVING viewCount>#{max} ORDER BY viewCount desc LIMIT #{size}")
    List<Map<String,String>> searchHotGoods(@Param("size") Integer size,
                                            @Param("time") String time,
                                            @Param("urls")String urls,
                                            @Param("max")Integer max);
}
