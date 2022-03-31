package com.hope.mode.proxy.statics;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by lijin on  2022/3/10
 */
@Slf4j
public class SmsServiceOneImpl implements SmsServiceOne {
    @Override
    public void send() {
        log.info("发短信");
    }
}
