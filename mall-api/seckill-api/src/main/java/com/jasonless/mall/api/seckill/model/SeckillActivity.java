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
@TableName(value = "seckill_activity")
public class SeckillActivity {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String activityName;
    private Integer type;   //活动分类 0shop秒杀、1 每日特价、2 大牌闪购、3 品类秒杀、4 节日活动
    private Date startTime;
    private Date endTime;
}
