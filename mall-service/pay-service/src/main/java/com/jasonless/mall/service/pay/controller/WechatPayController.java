package com.jasonless.mall.service.pay.controller;

import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.jasonless.mall.api.pay.model.PayLog;
import com.jasonless.mall.common.util.*;
import com.jasonless.mall.service.pay.service.PayLogService;
import com.jasonless.mall.service.pay.service.WeixinPayService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jasonless
 * @date 2020/12/16
 */
@RestController
@RequestMapping(value = "/wx")
public class WechatPayController {

    @Autowired
    private PayLogService payLogService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private WeixinPayService weixinPayService;

    @Autowired
    private Signature signature;

    //秘钥->MD5（skey）
    @Value("${payconfig.weixin.key}")
    private String skey;


    /*****
     * 预下单
     * ciptext:支付信息加密字符串  AES加密，包含验签
     */
    @GetMapping(value = "/pay")
    public ResponseResult<Map> pay(@RequestParam(value = "ciptext")String ciphertext) throws Exception {

        //数据解析，并验签校验
        Map<String, String> map = signature.security(ciphertext);
        //1分钱测试
        if(map!=null){
            Map<String, String> resultMap = weixinPayService.preOrder(map);
            resultMap.put("orderNumber",map.get("out_trade_no"));
            resultMap.put("money",map.get("total_fee"));
            return ResponseResult.ok(resultMap);
        }
        return ResponseResult.error("支付系统繁忙，请稍后再试!");
    }

    /***
     * 支付状态查询
     */
    @GetMapping(value = "/result/{outno}")
    public ResponseResult<PayLog> query(@PathVariable(value = "outno")String outno) throws Exception {
        PayLog payLog = weixinPayService.result(outno);
        return ResponseResult.ok(payLog);
    }

    /***
     * 记录支付结果
     * 执行事务消息发送
     */
    @GetMapping(value = "/result")
    public String payLog(HttpServletRequest request) throws Exception {

        //获取支付结果
        ServletInputStream is = request.getInputStream();
        //接收存储网络输入流(微信服务器返回的支付状态数据)
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        //缓冲区定义
        byte[] buffer = new byte[1024];
        int len = 0;
        //循环读取输入流，并写入到os中
        while ((len=is.read(buffer))!=-1){
            os.write(buffer,0,len);
        }

        //关闭资源
        os.close();
        is.close();

        //将支付结果转成xml的字符串
        String xmlResult = new String(os.toByteArray(),"utf-8");
        //将xmlResult转成Map
        Map<String, String> responseMap = WXPayUtil.xmlToMap(xmlResult);

        //记录日志
        int status = 7;//支付失败
        if(responseMap.get("return_code").equals(WXPayConstants.SUCCESS) && responseMap.get("result_code").equals(WXPayConstants.SUCCESS)){
            status=2;//已支付
        }
        PayLog payLog = new PayLog(responseMap.get("out_trade_no"),status,JSON.toJSONString(responseMap),responseMap.get("out_trade_no"),new Date());
        Message message = MessageBuilder.withPayload(JSON.toJSONString(payLog)).build();
        rocketMQTemplate.sendMessageInTransaction("rocket","log",message,null);

        //返回结果
        Map<String,String> resultMap = new HashMap<String,String>();
        resultMap.put("return_code","SUCCESS");
        resultMap.put("return_msg","OK");
        return WXPayUtil.mapToXml(resultMap);


    }


    /***
     * 退款通知结果
     */
    @RequestMapping(value = "/refund/result")
    public String refundResult(HttpServletRequest request) throws Exception{
        System.out.println("*****************************退款通知*********************************");
        //获取结果
        ServletInputStream is = request.getInputStream();
        //接收存储网络输入流(微信服务器返回的支付状态数据)
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        //缓冲区定义
        byte[] buffer = new byte[1024];
        int len = 0;
        //循环读取输入流，并写入到os中
        while ((len=is.read(buffer))!=-1){
            os.write(buffer,0,len);
        }

        //关闭资源
        os.close();
        is.close();

        //将结果转成xml的字符串
        String xmlResult = new String(os.toByteArray(),"utf-8");
        //将xmlResult转成Map
        Map<String, String> responseMap = WXPayUtil.xmlToMap(xmlResult);
        System.out.println("退款数据-xmlResult:"+xmlResult);

        //获取退款信息（加密了-AES）
        String reqinfo = responseMap.get("req_info");
        String key = MD5.md5(skey);
        byte[] decode = AESUtil.encryptAndDecrypt(Base64Util.decode(reqinfo), key, 2);
        System.out.println("退款解密后的数据："+new String(decode, "UTF-8"));

        //发送MQ消息，普通消息，非事务消息
        Message message = MessageBuilder.withPayload(JSON.toJSONString(responseMap)).build();
        rocketMQTemplate.send("lastrefundresult",message);

        //返回结果
        Map<String,String> resultMap = new HashMap<String,String>();
        resultMap.put("return_code","SUCCESS");
        resultMap.put("return_msg","OK");
        return WXPayUtil.mapToXml(resultMap);
    }

}
