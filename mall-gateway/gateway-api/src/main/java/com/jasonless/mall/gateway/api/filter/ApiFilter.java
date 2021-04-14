package com.jasonless.mall.gateway.api.filter;

import com.alibaba.fastjson.JSON;
import com.jasonless.mall.gateway.api.hot.HotQueue;
import com.jasonless.mall.gateway.api.permission.AuthorizationInterceptor;
import com.jasonless.mall.gateway.api.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LiuShiZeng
 * 2021/4/11
 * 写注释吧
 */
@Configuration
public class ApiFilter implements GlobalFilter, Ordered {

    @Autowired
    private HotQueue hotQueue;

    /***
     * 执行拦截处理      http://localhost:9001/mall/seckill/order?id&num
     *                 JWT
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        //获取uri
        String uri = request.getURI().getPath();

        if(uri.equals("/mall/user/info/login")){
            //放行
            return chain.filter(exchange);
        }

        //客户端IP
        String ip = IpUtil.getIp(request);
        //用户令牌
        String token = request.getHeaders().getFirst("authorization");
        //令牌校验
        Map<String, Object> resultMap = AuthorizationInterceptor.jwtVerify(token, ip);
        if(resultMap==null){
            endProcess(exchange,401,"no token");
        }

        if(uri.equals("/seckill/order")){
            //秒杀过滤
            seckillFilter(exchange, request, resultMap.get("username").toString());
        }

        //NOT_HOT 直接由后端服务处理
        return chain.filter(exchange);

//        //用户名
//        String username = "test";
//        //商品ID
//        String id = request.getQueryParams().getFirst("id");
//        //数量
//        Integer num =Integer.valueOf( request.getQueryParams().getFirst("num") );
//
//        //排队结果
//        int result = hotQueue.hotToQueue(username, id, num);
//
//        //QUEUE_ING、HAS_QUEUE
//        if(result==HotQueue.QUEUE_ING || result==HotQueue.HAS_QUEUE){
//            //响应状态码200
//            Map<String,Object> resultMap = new HashMap<String,Object>();
//            resultMap.put("type","hot");
//            resultMap.put("code",result);
//            exchange.getResponse().setStatusCode(HttpStatus.OK);
//            exchange.getResponse().setComplete();
//            exchange.getResponse().getHeaders().add("message", JSON.toJSONString(resultMap));
//        }
//
//        //NOT_HOT 直接由后端服务处理
//        return chain.filter(exchange);
    }

    /***
     * 结束程序
     * @param exchange
     * @param code
     * @param message
     */
    public void endProcess(ServerWebExchange exchange,Integer code,String message){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("code",code);
        resultMap.put("message",message);
        exchange.getResponse().setStatusCode(HttpStatus.OK);
        exchange.getResponse().setComplete();
        exchange.getResponse().getHeaders().add("message",JSON.toJSONString(resultMap));
    }


    /***
     * 秒杀过滤
     * @param exchange
     * @param request
     * @param username
     */
    private void seckillFilter(ServerWebExchange exchange, ServerHttpRequest request, String username) {
        //商品ID
        String id = request.getQueryParams().getFirst("id");
        //数量
        Integer num =Integer.valueOf( request.getQueryParams().getFirst("num") );

        //排队结果
        int result = hotQueue.hotToQueue(username, id, num);

        //QUEUE_ING、HAS_QUEUE
        if(result==HotQueue.QUEUE_ING || result==HotQueue.HAS_QUEUE){
            endProcess(exchange,result,"hot");
        }
    }

    @Override
    public int getOrder() {
        return 10001;
    }
}
