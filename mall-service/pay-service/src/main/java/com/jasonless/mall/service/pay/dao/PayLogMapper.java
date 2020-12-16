package com.jasonless.mall.service.pay.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jasonless.mall.api.pay.model.PayLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Jasonless
 * @date 2020/12/16
 */
@Repository
@Mapper
public interface PayLogMapper extends BaseMapper<PayLog> {
}
