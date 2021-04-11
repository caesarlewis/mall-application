package com.jasonless.mall.service.dw.controller;

import com.jasonless.mall.api.dw.model.HotGoods;
import com.jasonless.mall.common.util.ResponseResult;
import com.jasonless.mall.service.dw.service.HotGoodsService;
import com.jasonless.mall.service.dw.util.DruidPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author LiuShiZeng
 * 2021/4/11
 * 写注释吧
 */
@RestController
@RequestMapping(value = "/hot/goods")
public class HotGoodsController {

    @Autowired
    private HotGoodsService hotGoodsService;


    /****
     * 热点商品查询
     */
    @PostMapping("/search/{size}/{hour}/{max}")
    public ResponseResult<List<Map<String,String>>> searchHot(
            @PathVariable(value = "size")Integer size,
            @PathVariable(value = "hour")Integer hour,
            @PathVariable(value = "max")Integer max,
            @RequestBody(required = false) String[] ids){
        //集合查询前N条
        List<Map<String,String>> hotGoods = hotGoodsService.searchHotGoods(size,hour,ids,max);
        return ResponseResult.ok(hotGoods);
    }

    /****
     * 指定时间的历史数据查询,排除指定数据
     */
    @PostMapping("/search/{size}/{hour}")
    public ResponseResult<List<HotGoods>> history(@PathVariable(value = "size")Integer size,
                                              @PathVariable(value = "hour")Integer hour,@RequestBody String[] ids){
        //集合查询前N条
        List<HotGoods> hotGoods = hotGoodsService.search(size,hour,ids);
        return ResponseResult.ok(hotGoods);
    }

    /****
     * 指定时间的历史数据查询
     */
    @GetMapping("/search/{size}/{hour}")
    public ResponseResult<List<HotGoods>> history(@PathVariable(value = "size")Integer size,
                                              @PathVariable(value = "hour")Integer hour){
        //集合查询前N条
        List<HotGoods> hotGoods = hotGoodsService.search(size,hour);
        return ResponseResult.ok(hotGoods);
    }

    /***
     * 分页排序查询
     * @return
     */
    @GetMapping("/{page}/{size}/{sort}/{type}")
    public ResponseResult<DruidPage<List<HotGoods>>> page(@PathVariable(value = "page")Integer page,
                                                      @PathVariable(value = "size")Integer size,
                                                      @PathVariable(value = "sort")String sort,
                                                      @PathVariable(value = "type")String sortType){
        //集合查询
        DruidPage<List<HotGoods>> pageInfo = hotGoodsService.pageListSort(page,size,sort,sortType);
        return ResponseResult.ok(pageInfo);
    }

    /***
     * 分页查询
     * @return
     */
    @GetMapping("/{page}/{size}")
    public ResponseResult<DruidPage<List<HotGoods>>> page(@PathVariable(value = "page")Integer page,
                                                      @PathVariable(value = "size")Integer size){
        //集合查询
        DruidPage<List<HotGoods>> pageInfo = hotGoodsService.pageList(page,size);
        return ResponseResult.ok(pageInfo);
    }

    /***
     * 查询前N条记录
     * @return
     */
    @GetMapping("/top/{size}")
    public ResponseResult<List<HotGoods>> topNum(@PathVariable(value = "size")Integer size){
        //集合查询前N条
        List<HotGoods> hotGoods = hotGoodsService.topNum(size);
        return ResponseResult.ok(hotGoods);
    }

    /***
     * 集合查询
     * @return
     */
    @GetMapping
    public ResponseResult<List<HotGoods>> list(){
        //集合查询
        List<HotGoods> goods = hotGoodsService.list();
        return ResponseResult.ok(goods);
    }

}
