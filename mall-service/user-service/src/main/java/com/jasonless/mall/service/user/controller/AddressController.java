package com.jasonless.mall.service.user.controller;

import com.jasonless.mall.common.util.ResponseResult;
import com.jasonless.mall.service.user.service.AddressService;
import com.jasonless.mall.user.api.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Jasonless
 * @date 2020/12/14
 */
@RestController
@RequestMapping(value = "/address")
@CrossOrigin
public class AddressController {

    @Autowired
    private AddressService addressService;

    /***
     * 用户地址列表查询
     * @return
     */
    @GetMapping(value = "/list")
    public ResponseResult<List<Address>> list(){
        String userName = "gp";
        List<Address> addresses = addressService.list(userName);
        return ResponseResult.ok(addresses);
    }

}
