package com.jasonless.mall.service.cart.controller;

import com.google.common.collect.Lists;
import com.jasonless.mall.api.cart.model.Cart;
import com.jasonless.mall.common.util.ResponseResult;
import com.jasonless.mall.service.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jasonless
 * @date 2020/12/11
 */
@RestController
@RequestMapping(value = "/cart")
@CrossOrigin
public class CartController {

    @Autowired
    private CartService cartService;

    /***
     * 添加购物车
     * id: skuid
     */
    @GetMapping(value = "/{id}/{num}")
    public ResponseResult add(@PathVariable(value = "id")String id,@PathVariable(value = "num")Integer num){
        //用户名字
        String userName="gp";
        //加入购物车
        cartService.add(id,userName,num);
        return ResponseResult.ok();
    }

    /****
     * 购物车列表
     * @return
     */
    @GetMapping(value = "/list")
    public ResponseResult<List<Cart>> list(){
        String userName = "gp";
        List<Cart> carts = cartService.list(userName);
        return ResponseResult.ok(carts);
    }

    /***
     * 购物车数据
     */
    @PostMapping(value = "/list")
    public ResponseResult<List<Cart>> list(@RequestBody List<String> ids){
        //购物车集合
        List<Cart> carts = Lists.newArrayList(cartService.list(ids));
        return ResponseResult.ok(carts);
    }

    @DeleteMapping
    public ResponseResult delete(@RequestBody List<String> ids){
        //删除购物车集合
        cartService.delete(ids);
        return ResponseResult.ok();
    }


}
