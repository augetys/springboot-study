package com.hope.mode.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by lijin on  2022/3/11
 */
@Slf4j
public class AliPay implements Payment {
    @Override
    public void pay(Long order, double amount) {
       log.info("----支付宝支付----");
       log.info("支付宝支付111元");
    }
}
