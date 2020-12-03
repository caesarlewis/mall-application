package com.jasonless.mall.api.goods.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 推广商品
 * @author Jasonless
 * @date 2020/12/3
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//MyBatisPlus表映射注解
@TableName(value = "ad_items")
public class AdItems {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer type;
    private String skuId;
    private Integer sort;

}
