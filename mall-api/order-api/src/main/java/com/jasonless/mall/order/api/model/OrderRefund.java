package com.jasonless.mall.order.api.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
@TableName(value = "order_refund")
public class OrderRefund implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String orderNo;
    private Integer refundType;
    private String orderSkuId;
    private String username;
    private Integer status;
    private Date createTime;
    private Integer money;
}
