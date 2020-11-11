package com.jasonless.mall.api.goods.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jasonless
 * @date 2020/11/11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//MyBatisPlus表映射注解
@TableName(value = "category")
public class Category {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer sort;
    private Integer parentId;

}
