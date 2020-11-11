package com.jasonless.mall.api.goods.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * 品牌实体类
 * @author Jasonless
 * @date 2020/10/26
 */
@TableName(value = "brand")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String image;

    private String initial;

    private Integer sort;


}
