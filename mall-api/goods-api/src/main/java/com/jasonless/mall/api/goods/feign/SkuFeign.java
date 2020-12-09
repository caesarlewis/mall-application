package com.jasonless.mall.api.goods.feign;

import com.jasonless.mall.api.goods.entity.Sku;
import com.jasonless.mall.common.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jasonless
 * @date 2020/12/7
 */
@FeignClient(value = "mall-goods")
public interface SkuFeign {

    /****
     * 指定分类下的推广产品列表
     */
    @GetMapping(value = "/sku/aditems/type")
    public List<Sku> typeItems(@RequestParam(value = "id") Integer id);

    /****
     * 删除指定分类下的推广产品列表
     */
    @DeleteMapping(value = "/sku/aditems/type/{id}")
    public ResponseResult deleteTypeItems(@PathVariable(value = "id")Integer id);

    /****
     * 修改指定分类下的推广产品列表
     */
    @PutMapping(value = "/sku/aditems/type/{id}")
    public ResponseResult updateTypeItems(@PathVariable(value = "id")Integer id);

}
