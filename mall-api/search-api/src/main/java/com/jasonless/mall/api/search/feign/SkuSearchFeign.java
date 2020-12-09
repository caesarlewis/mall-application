package com.jasonless.mall.api.search.feign;

import com.jasonless.mall.api.search.model.SkuEs;
import com.jasonless.mall.common.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

}
