package com.jasonless.mall.service.user.controller;

import com.jasonless.mall.common.util.JwtToken;
import com.jasonless.mall.common.util.ResponseResult;
import com.jasonless.mall.service.user.service.UserInfoService;
import com.jasonless.mall.user.api.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LiuShiZeng
 * 2021/4/12
 * 写注释吧
 */
@RestController
@RequestMapping(value = "/user/info")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;


    /****
     * 登录
     */
    @PostMapping(value = "/login")
    public ResponseResult<String> login(@RequestParam String username, @RequestParam String pwd){
        //登录
        UserInfo userInfo = userInfoService.getById(username);
        if(userInfo!=null){
            //匹配密码是否一致
            if(userInfo.getPassword().equals(pwd)){
                //封装用户信息实现加密
                Map<String,Object> dataMap = new HashMap<String,Object>();
                dataMap.put("username",userInfo.getUsername());
                dataMap.put("name",userInfo.getName());
                dataMap.put("roles",userInfo.getRoles());

                //创建令牌
                String token = JwtToken.createToken(dataMap);
                return ResponseResult.ok(token);
            }
            //账号密码不匹配
            return ResponseResult.error("账号或者密码错误");
        }
        return ResponseResult.error("账号不存在");
    }

}
