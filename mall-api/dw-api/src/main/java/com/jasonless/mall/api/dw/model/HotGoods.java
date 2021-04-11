package com.jasonless.mall.api.dw.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author LiuShiZeng
 * 2021/4/11
 * 写注释吧
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "mslogs")
public class HotGoods {
    //IP
    private String ip;
    //访问的uri
    private String uri;
    //时间.
    @TableField("__time")
    private Date accesstime;
}
