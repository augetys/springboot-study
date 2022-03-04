package com.hope.concurrent.sync;

/**
 * Created by lijin on  2022/3/4
 */
public class SynchronizedObjectLock03 implements Runnable {

    static SynchronizedObjectLock03 instance1 = new SynchronizedObjectLock03();
    static SynchronizedObjectLock03 instance2 = new SynchronizedObjectLock03();

    @Override
    public void run() {
        method();
    }

    public static synchronized void method() {
        // synchronized用在静态方法上，默认的锁就是当前所在的Class类，所以无论是哪个线程访问它，需要的锁都只有一把
        System.out.println("我是线程" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "结束");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance2);
        t1.start();
        t2.start();
    }

}
