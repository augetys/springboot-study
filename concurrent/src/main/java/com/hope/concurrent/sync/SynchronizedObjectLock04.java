package com.hope.concurrent.sync;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by lijin on  2022/3/4
 */
@Slf4j
public class SynchronizedObjectLock04 implements Runnable {

    static SynchronizedObjectLock instance1 = new SynchronizedObjectLock();
    static SynchronizedObjectLock instance2 = new SynchronizedObjectLock();

    @Override
    public void run() {
        // 所有线程需要的锁都是同一把
        synchronized (SynchronizedObjectLock04.class) {
            log.info("我是线程" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info(Thread.currentThread().getName() + "结束");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance2);
        t1.start();
        t2.start();
    }
}
