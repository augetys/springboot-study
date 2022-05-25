package com.hope.concurrent.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lijin on  2022/5/16
 */
public class Driver {

    public static void main(String[] args) throws InterruptedException {

        // 开始装车信号
        CountDownLatch startSignal = new CountDownLatch(1);
        // 装车完毕信号
        CountDownLatch doneSignal = new CountDownLatch(4);

        // 初始化四个搬运工
        for (int i = 0; i < 4; ++i) {
            new Thread(new WorkerRunnable(startSignal, doneSignal), "Worker-" + i).start();
        }

        // 司机做准备工作，比如把车开到指定地方
        doSomethingElse();

        // 准备完了，发信号让开始搬运
        startSignal.countDown();

        // 司机等待所有搬运工完成
        doneSignal.await();

        // 完成
        doSomethingElse2();
    }

    static void doSomethingElse() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("司机准备工作完成");
    }

    static void doSomethingElse2() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("滴，滴滴~ 老司机发车了");
    }
}
