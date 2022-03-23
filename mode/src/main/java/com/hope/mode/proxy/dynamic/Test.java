package com.hope.mode.proxy.dynamic;


/**
 * Created by lijin on  2022/3/10
 */
public class Test {
    public static void main(String[] args) {
        SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        // 调用时会走invoke
        smsService.send();
        smsService.receive();
    }
}
