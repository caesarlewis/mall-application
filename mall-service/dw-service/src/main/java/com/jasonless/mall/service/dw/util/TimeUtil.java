package com.jasonless.mall.service.dw.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author LiuShiZeng
 * 2021/4/11
 * 写注释吧
 */
public class TimeUtil {

    public static final String FORMAT1 = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT2 = "yyyy-MM-dd";
    public static final String FORMAT3 = "yyyy年MM月dd日 HH时mm分ss秒";
    public static final String FORMAT4 = "yyyy年MM月dd日";
    public static final String UNIT_HOUR = "hour";
    public static final String UNIT_DAY = "day";

    public static final DateTimeFormatter DATETIMEFORMATTER = DateTimeFormatter.ofPattern(FORMAT1);

    /***
     * 当前时间增加N Unit
     */
    public static String beforeTime(String unit,Integer num){
        //1小时为单位
        long times = 3600000;
        if(unit.equalsIgnoreCase(UNIT_HOUR)){
            times=times*num;
        }else if(unit.equalsIgnoreCase(UNIT_DAY)){
            times=times*24*num;
        }

        long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        return DATETIMEFORMATTER.format(LocalDateTime.ofEpochSecond(milliSecond-times,0,ZoneOffset.of("+8")));
    }
}
