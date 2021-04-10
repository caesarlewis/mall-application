package com.jasonless.mall.page.web.service;

/**
 * @author LiuShiZeng
 * 2021/4/10
 * 写注释吧
 */
public interface SeckillPageService {

    /***
     * 生成静态页
     */
    void html(String id) throws Exception;

    //删除秒杀详情页
    void delete(String id);
}
