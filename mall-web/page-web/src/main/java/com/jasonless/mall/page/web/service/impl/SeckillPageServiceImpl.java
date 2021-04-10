package com.jasonless.mall.page.web.service.impl;

import com.jasonless.mall.api.seckill.feign.SeckillGoodsFeign;
import com.jasonless.mall.api.seckill.model.SeckillGoods;
import com.jasonless.mall.common.util.ResponseResult;
import com.jasonless.mall.page.web.service.SeckillPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LiuShiZeng
 * 2021/4/10
 * 写注释吧
 */
@Service
public class SeckillPageServiceImpl implements SeckillPageService {

    @Autowired
    private TemplateEngine templateEngine;


    @Value("${seckillpath}")
    private String seckillpath;

    @Autowired
    private SeckillGoodsFeign seckillGoodsFeign;

    /****
     * 生成静态页
     */
    @Override
    public void html(String id) throws Exception {
        //加载数据
        Map<String,Object> dataMap = dataLoad(id);

        //创建Thymeleaf容器对象
        Context context = new Context();
        //设置页面数据模型
        context.setVariables(dataMap);
        //文件名字  id.html
        File dest = new File(seckillpath, id + ".html");
        PrintWriter writer = new PrintWriter(dest, "UTF-8");
        //生成页面
        templateEngine.process("item", context, writer);
    }

    /***
     * 加载数据
     * @param id
     * @return
     */
    private Map<String,Object> dataLoad(String id) {
        ResponseResult<SeckillGoods> goodsResp = seckillGoodsFeign.one(id);
        //将商品信息存入到Map中
        if(goodsResp.getData()!=null){
            Map<String,Object> dataMap = new HashMap<String,Object>();
            dataMap.put("item",goodsResp.getData());
            return dataMap;
        }
        return null;
    }

    @Override
    public void delete(String id) {
        //创建要删除的文件对象
        File file = new File(seckillpath,id+".html");
        if(file.exists()){
            file.delete();
        }
    }
}
