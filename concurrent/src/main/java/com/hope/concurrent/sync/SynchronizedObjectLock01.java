package com.hope.concurrent.sync;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by lijin on  2022/3/4
 */
@Slf4j
public class SynchronizedObjectLock01 implements Runnable {

    static SynchronizedObjectLock01 instance = new SynchronizedObjectLock01();

    // 创建2把锁
    Object block1 = new Object();
    Object block2 = new Object();

    @Override
    public void run() {

        // 这个代码块使用的是第一把锁，当他释放后，后面的代码块由于使用的是第二把锁，因此可以马上执行
        synchronized (block1) {
            log.info("block1锁,我是线程" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("block1锁," + Thread.currentThread().getName() + "结束");
        }

        synchronized (block2) {
            log.info("block2锁,我是线程" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("block2锁," + Thread.currentThread().getName() + "结束");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
    }
}
