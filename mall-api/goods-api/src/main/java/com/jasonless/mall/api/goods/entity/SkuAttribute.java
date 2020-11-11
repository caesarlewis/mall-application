package com.jasonless.mall.api.goods.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Jasonless
 * @date 2020/11/11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//MyBatisPlus表映射注解
@TableName(value = "sku_attribute")
public class SkuAttribute {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String options;
    private Integer sort;

    //对应分类
    @TableField(exist = false)
    private List<Category> categories;

}
