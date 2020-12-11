package com.jasonless.mall.api.search.feign;

import com.jasonless.mall.api.search.model.SkuEs;
import com.jasonless.mall.common.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Jasonless
 * @date 2020/12/7
 */
@FeignClient(value = "mall-search")
public interface SkuSearchFeign {

    /*****
     * 增加索引
     */
    @PostMapping(value = "/search/add")
    ResponseResult add(@RequestBody SkuEs skuEs);

    /***
     * 删除索引
     */
    @DeleteMapping(value = "/search/del/{id}")
    ResponseResult del(@PathVariable(value = "id")String id);

    /****
     * 商品搜索
     */
    @GetMapping
    ResponseResult<Map<String,Object>> search(@RequestParam Map<String,Object> searchMap);

}
