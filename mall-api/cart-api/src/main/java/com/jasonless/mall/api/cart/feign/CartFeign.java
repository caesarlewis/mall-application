package com.jasonless.mall.api.cart.feign;

import com.jasonless.mall.api.cart.model.Cart;
import com.jasonless.mall.common.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Jasonless
 * @date 2020/12/14
 */
@FeignClient(value = "mall-cart")
public interface CartFeign {

    /***
     * 购物车数据
     */
    @PostMapping(value = "/cart/list")
    ResponseResult<List<Cart>> list(@RequestBody List<String> ids);

    /***
     * 删除指定购物车
     */
    @DeleteMapping(value = "/cart")
    ResponseResult delete(@RequestBody List<String> ids);

}
