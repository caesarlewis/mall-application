package com.jasonless.mall.api.pay.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Jasonless
 * @date 2020/12/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "pay_log")
public class PayLog {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private Integer status;
    private String content;
    private String payId;
    private Date createTime;

}
