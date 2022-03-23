package com.hope.mode.proxy.statics;

/**
 * Created by lijin on  2022/3/10
 */
public class SmsServiceOneImpl implements SmsServiceOne {
    @Override
    public void send() {
        System.out.println("发短信");
    }
}
