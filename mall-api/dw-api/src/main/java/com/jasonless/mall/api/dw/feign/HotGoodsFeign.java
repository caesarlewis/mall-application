package com.jasonless.mall.api.dw.feign;

import com.jasonless.mall.common.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * @author LiuShiZeng
 * 2021/4/11
 * 写注释吧
 */
@FeignClient(value = "mall-dw")
public interface HotGoodsFeign {

    /****
     * 热点商品查询
     */
    @PostMapping("/hot/goods/search/{size}/{hour}/{max}")
    public ResponseResult<List<Map<String,String>>> searchHot(
            @PathVariable(value = "size")Integer size,
            @PathVariable(value = "hour")Integer hour,
            @PathVariable(value = "max")Integer max,
            @RequestBody(required = false) String[] ids);
}
