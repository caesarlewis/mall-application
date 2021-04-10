package com.jasonless.mall.service.pay.config;

import com.github.wxpay.sdk.IWXPayDomain;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author LiuShiZeng
 * 2021/4/10
 * 写注释吧
 */
@Component
public class WeixinPayConfig extends WXPayConfig {

    //微信支付信息
    @Value("${payconfig.weixin.appId}")
    //应用ID
    private String appId;
    @Value("${payconfig.weixin.mchID}")
    //商户号
    private String mchID;
    @Value("${payconfig.weixin.key}")
    //秘钥
    private String key;
    @Value("${payconfig.weixin.notifyUrl}")
    //回调地址
    private String notifyUrl;
    @Value("${payconfig.weixin.certPath}")
    //证书路径
    private String certPath;
    //证书字节数组
    private byte[] certData;

    @Override
    public String getAppID() {
        return this.appId;
    }

    @Override
    public String getMchID() {
        return this.mchID;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public InputStream getCertStream() {
        /****
         * 加载证书
         */
        if(certData==null){
            synchronized (WeixinPayConfig.class){
                try {
                    if(certData==null) {
                        File file = new File(certPath);
                        InputStream certStream = new FileInputStream(file);
                        this.certData = new byte[(int) file.length()];
                        certStream.read(this.certData);
                        certStream.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    /***
     * 获取WXPayDomain, 用于多域名容灾自动切换
     * @return
     */
    @Override
    public IWXPayDomain getWXPayDomain() {
        // 这个方法需要这样实现, 否则无法正常初始化WXPay
        IWXPayDomain iwxPayDomain = new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {
            }
            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new IWXPayDomain.DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        };
        return iwxPayDomain;
    }
}
