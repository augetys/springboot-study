package com.hope.mode.strategy;

/**
 * Created by lijin on  2022/3/11
 */
public class AliPay implements Payment {
    @Override
    public void pay(Long order, double amount) {
        System.out.println("----支付宝支付----");
        System.out.println("支付宝支付111元");
    }
}
