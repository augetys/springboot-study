package com.hope.mode.proxy.dynamic;


import lombok.extern.slf4j.Slf4j;

/**
 * Created by lijin on  2022/3/10
 */
@Slf4j
public class SmsServiceImpl implements SmsService {
    @Override
    public void send() {
        log.info("发短信");
    }

    @Override
    public void receive() {
        log.info("接收短信");
    }
}
