package com.hope.concurrent.volatileexample;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by lijin on  2022/3/4
 */
@Slf4j
public class VolatileTest {

    int a = 1;
    int b = 2;

    public void change() {
        a = 3;
        b = a;
    }

    public void print() {
        log.info("b=" + b + ";a=" + a);
    }

    public static void main(String[] args) {
        while (true) {
            final VolatileTest test = new VolatileTest();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.change();
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.print();
                }
            }).start();
        }
    }
}
