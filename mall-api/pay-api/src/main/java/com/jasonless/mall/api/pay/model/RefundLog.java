package com.jasonless.mall.api.pay.model;

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
@TableName(value = "refund_log")
public class RefundLog implements Serializable {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String orderNo;
    private String outRefundNo;
    private Integer money;
    private Date createTime;
}
