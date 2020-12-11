package com.jasonless.mall.search.web.controller;

import com.jasonless.mall.api.search.feign.SkuSearchFeign;
import com.jasonless.mall.common.util.ResponseResult;
import com.jasonless.mall.common.util.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author Jasonless
 * @date 2020/12/9
 */
@Controller
@RequestMapping(value = "/web/search")
public class SearchController {

    @Autowired
    private SkuSearchFeign skuSearchFeign;

    /**
     * 搜索页面跳转
     * @return
     */
    @GetMapping
    public String search(Model model, @RequestParam Map<String,Object> searchMap){
        //搜索
        ResponseResult<Map<String,Object>> result = skuSearchFeign.search(searchMap);
        //数据存入Model
        model.addAttribute("result",result.getData());

        model.addAttribute("url",UrlUtils.map2url("/web/search",searchMap,"page"));
        model.addAttribute("urlsort", UrlUtils.replateUrlParameter(model.getAttribute("url").toString(),"sm","sfield"));
        //当前地址
        model.addAttribute("searchMap", UrlUtils.map2url("/web/search",searchMap));
        return "search";
    }

}
