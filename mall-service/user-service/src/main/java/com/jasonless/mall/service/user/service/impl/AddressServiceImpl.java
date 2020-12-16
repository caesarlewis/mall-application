package com.jasonless.mall.service.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasonless.mall.service.user.mapper.AddressMapper;
import com.jasonless.mall.service.user.service.AddressService;
import com.jasonless.mall.user.api.model.Address;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Jasonless
 * @date 2020/12/14
 */
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    /***
     * 根据用户名查询地址列表集合
     * @param userName
     * @return
     */
    @Override
    public List<Address> list(String userName) {


        QueryWrapper<Address> queryWrapper = new QueryWrapper<Address>();
        queryWrapper.eq("username",userName);
        return addressMapper.selectList(queryWrapper);
    }
}
