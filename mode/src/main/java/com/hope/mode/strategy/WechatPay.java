package com.hope.mode.strategy;

/**
 * Created by lijin on  2022/3/11
 */
public class WechatPay implements Payment{
    @Override
    public void pay(Long order, double amount) {
        System.out.println("----微信支付----");
        System.out.println("微信宝支付111元");
    }
}
