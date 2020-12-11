package com.jasonless.mall.page.web.controller;

import com.jasonless.mall.common.util.ResponseResult;
import com.jasonless.mall.page.web.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jasonless
 * @date 2020/12/10
 */
@RestController
@RequestMapping(value = "/page")
public class PageController {

    @Autowired
    private PageService pageService;

    /***
     * 生成静态页
     */
    @GetMapping(value = "/{id}")
    public ResponseResult html(@PathVariable(value = "id")String id) throws Exception{
        pageService.html(id);
        return ResponseResult.ok();
    }

}
