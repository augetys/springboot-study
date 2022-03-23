package com.hope.mode.proxy.dynamic;


/**
 * Created by lijin on  2022/3/10
 */
public class SmsServiceImpl implements SmsService {
    @Override
    public void send() {
        System.out.println("发短信");
    }

    @Override
    public void receive() {
        System.out.println("接收短信");
    }
}
