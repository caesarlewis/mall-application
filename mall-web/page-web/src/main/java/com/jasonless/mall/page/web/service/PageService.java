package com.jasonless.mall.page.web.service;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * @author Jasonless
 * @date 2020/12/10
 */
public interface PageService {
    //生成静态页
    void html(String id) throws FileNotFoundException, UnsupportedEncodingException;
}
