package com.jasonless.mall.service.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jasonless.mall.user.api.model.Address;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jasonless
 * @date 2020/12/14
 */
@Repository
@Mapper
public interface AddressMapper extends BaseMapper<Address> {


}
