package com.jasonless.mall.page.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.jasonless.mall.api.goods.model.Category;
import com.jasonless.mall.api.goods.model.Product;
import com.jasonless.mall.api.goods.model.Sku;
import com.jasonless.mall.api.goods.feign.CategoryFeign;
import com.jasonless.mall.api.goods.feign.SpuFeign;
import com.jasonless.mall.common.util.ResponseResult;
import com.jasonless.mall.page.web.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jasonless
 * @date 2020/12/10
 */
public class PageServiceImpl implements PageService {

    @Autowired
    private SpuFeign spuFeign;

    @Autowired
    private CategoryFeign categoryFeign;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${pagepath}")
    private String pagepah;

    /****
     * 生成静态页
     * @param id
     */
    @Override
    public void html(String id) throws FileNotFoundException, UnsupportedEncodingException {
        //加载数据
        Map<String,Object> dataMap = dataLoad(id);

        //创建Thymeleaf容器对象
        Context context = new Context();
        //设置页面数据模型
        context.setVariables(dataMap);
        //文件名字  id.html
        File dest = new File(pagepah, id + ".html");
        PrintWriter writer = new PrintWriter(dest, "UTF-8");
        //生成页面
        templateEngine.process("item", context, writer);
    }


    /***
     * 数据加载
     * @param id
     * @return
     */
    public Map<String, Object> dataLoad(String id) {
        //查询商品数据
        ResponseResult<Product> respProduct = spuFeign.one(id);
        Product product = respProduct.getData();
        if (product != null) {
            //数据模型
            Map<String, Object> dataMap = new HashMap<String, Object>();
            //Spu
            dataMap.put("spu", product.getSpu());
            //图片
            dataMap.put("images", product.getSpu().getImages().split(","));
            //属性
            dataMap.put("attrs", JSON.parseObject(product.getSpu().getAttributeList()));

            //三级分类查询
            ResponseResult<Category> one = categoryFeign.one(product.getSpu().getCategoryOneId());
            ResponseResult<Category> two = categoryFeign.one(product.getSpu().getCategoryTwoId());
            ResponseResult<Category> three = categoryFeign.one(product.getSpu().getCategoryThreeId());
            dataMap.put("one", one.getData());
            dataMap.put("two", two.getData());
            dataMap.put("three", three.getData());

            //Sku集合转JSON
            List<Sku> skus = product.getSkus();
            List<Map<String, Object>> skuList = new ArrayList<Map<String, Object>>();
            for (Sku sku : skus) {
                Map<String, Object> skuMap = new HashMap<String, Object>();
                skuMap.put("id", sku.getId());
                skuMap.put("name", sku.getName());
                skuMap.put("price", sku.getPrice());
                skuMap.put("attr", sku.getSkuAttribute());
                //添加到集合中
                skuList.add(skuMap);
            }
            dataMap.put("skulist", skuList);
            return dataMap;
        }
        return null;
    }


}
