package com.hope.mode.strategy;

/**
 * Created by lijin on  2022/3/11
 */
public interface Payment {
    void pay(Long order, double amount);
}
