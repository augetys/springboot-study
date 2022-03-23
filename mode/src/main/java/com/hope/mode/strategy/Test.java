package com.hope.mode.strategy;

/**
 * Created by lijin on  2022/3/11
 */
public class Test {
    public static void main(String[] args) {
        //选择支付方式
        PayType payType = PayType.getByCode("2");
        //进行支付
        payType.get().pay((long) 1,1);
    }
}
