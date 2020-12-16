package com.jasonless.mall.api.goods.feign;

import com.jasonless.mall.api.goods.model.Category;
import com.jasonless.mall.common.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Jasonless
 * @date 2020/12/10
 */
@FeignClient(value = "mall-goods")
public interface CategoryFeign {

    /****
     * 根据分类查询分类信息
     */
    @GetMapping(value = "/category/{id}")
    ResponseResult<Category> one(@PathVariable(value = "id")Integer id);

}
