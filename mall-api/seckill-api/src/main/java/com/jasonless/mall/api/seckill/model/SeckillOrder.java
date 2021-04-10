package com.jasonless.mall.api.seckill.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author LiuShiZeng
 * 2021/4/10
 * 写注释吧
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//MyBatisPlus表映射注解
@TableName(value = "seckill_order")
public class SeckillOrder {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String seckillGoodsId;
    private String weixinTransactionId;
    private String username;
    private Integer money;
    private Integer status;
    private Date createTime;
    private Date payTime;
    private Integer num;    //抢购数量
}
