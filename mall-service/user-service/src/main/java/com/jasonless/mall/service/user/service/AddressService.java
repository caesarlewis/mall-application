package com.jasonless.mall.service.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jasonless.mall.user.api.model.Address;

import java.util.List;

/**
 * @author Jasonless
 * @date 2020/12/14
 */
public interface AddressService extends IService<Address> {


    //根据用户名查询地址列表集合
    List<Address> list(String userName);

}
