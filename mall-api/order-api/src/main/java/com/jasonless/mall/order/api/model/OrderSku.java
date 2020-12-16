package com.jasonless.mall.order.api.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jasonless
 * @date 2020/12/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "order_sku")
public class OrderSku {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String image;
    private String skuId;
    private String orderId;
    private String name;
    private Integer price;
    private Integer num;
    private Integer money;

}
