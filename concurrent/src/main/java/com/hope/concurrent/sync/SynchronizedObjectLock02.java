package com.hope.concurrent.sync;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by lijin on  2022/3/4
 */
@Slf4j
public class SynchronizedObjectLock02 implements Runnable {

    static SynchronizedObjectLock02 instance1 = new SynchronizedObjectLock02();
    static SynchronizedObjectLock02 instance2 = new SynchronizedObjectLock02();

    @Override
    public void run() {
        method();
    }

    public synchronized void method() {
        // synchronized修饰普通方法，锁对象默认为this
        log.info("我是线程" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(Thread.currentThread().getName() + "结束");
    }

    public static void main(String[] args) {
        // t1和t2对应的this是两个不同的实例，所以代码不会串行
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance2);
        t1.start();
        t2.start();
    }

}
