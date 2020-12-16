package com.jasonless.mall.api.goods.feign;

import com.jasonless.mall.api.goods.model.Product;
import com.jasonless.mall.common.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Jasonless
 * @date 2020/12/10
 */
@FeignClient("mall-goods")
public interface SpuFeign {

    /***
     * 根据ID查询
     */
    @GetMapping(value = "/spu/product/{id}")
    ResponseResult<Product> one(@PathVariable(value = "id")String id);

}
