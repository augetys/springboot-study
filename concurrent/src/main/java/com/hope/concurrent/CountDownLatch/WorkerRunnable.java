package com.hope.concurrent.CountDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by lijin on  2022/5/16
 */
public class WorkerRunnable implements Runnable {
    private final CountDownLatch startSignal;

    private final CountDownLatch doneSignal;

    public WorkerRunnable(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    @Override
    public void run() {
        // 搬运工等待准备工作完成
        try {
            startSignal.await();

            // 开始工作
            doWork();

            // 装完之后给司机发个信号
            doneSignal.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    void doWork() throws InterruptedException {
        // 模拟每个工人的装车速度
        Random random = new Random();
        Thread.sleep((long) (random.nextDouble() * 5000));

        System.out.println(Thread.currentThread().getName() + "装完了");
    }
}
