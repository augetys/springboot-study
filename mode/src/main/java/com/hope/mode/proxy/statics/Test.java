package com.hope.mode.proxy.statics;

/**
 * Created by lijin on  2022/3/10
 */
public class Test {
    public static void main(String[] args) {
        SmsImplProxyOne smsImplProxy = new SmsImplProxyOne(new SmsServiceOneImpl());
        smsImplProxy.send();
    }
}
