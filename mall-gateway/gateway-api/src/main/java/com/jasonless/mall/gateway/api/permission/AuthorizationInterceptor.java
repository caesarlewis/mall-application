package com.jasonless.mall.gateway.api.permission;

import com.jasonless.mall.common.util.JwtToken;
import com.jasonless.mall.common.util.MD5;

import java.util.Map;

/**
 * @author LiuShiZeng
 * 2021/4/12
 * 写注释吧
 */
public class AuthorizationInterceptor {

    /***
     * 令牌解析
     */
    public static Map<String, Object> jwtVerify(String token,String clientIp){
        try {
            //token解析
            Map<String, Object> resultMap = JwtToken.parseToken(token);
            //令牌中的IP
            String jwtip = resultMap.get("ip").toString();

            //IP校验
            clientIp = MD5.md5(clientIp);
            if(clientIp.equals(jwtip)){
                return resultMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
