package com.example.javabase.concurrent.reentrantlock;

import java.sql.Time;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiaobo
 * @description
 * @date 2023/4/6 16:29
 */
public class TestReentrantLock {

    private static int count = 5;
    private static Lock lock = new ReentrantLock();
    public static void buyTicket() {
        String name = Thread.currentThread().getName();
        lock.lock();
        try {
            if (count > 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name+" 买了第"+count--+"张票");
            } else {
                System.out.println(name+"  票卖完了");
            }
        } finally {
            lock.unlock();
        }

    }


    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i <20; i++) {
            new Thread(()->{
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                buyTicket();
            }).start();
        }
        countDownLatch.countDown();
    }
}
