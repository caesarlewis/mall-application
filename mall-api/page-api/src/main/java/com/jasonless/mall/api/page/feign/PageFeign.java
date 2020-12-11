package com.jasonless.mall.api.page.feign;

import com.jasonless.mall.common.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Jasonless
 * @date 2020/12/10
 */
@FeignClient(value = "mall-page-web")
public interface PageFeign {
    /***
     * 生成静态页
     */
    @GetMapping(value = "/page/{id}")
    ResponseResult html(@PathVariable(value = "id")String id) throws Exception;
}
