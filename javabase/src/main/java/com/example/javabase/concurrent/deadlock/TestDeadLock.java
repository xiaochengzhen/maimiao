package com.example.javabase.concurrent.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * @author xiaobo
 * @description
 * @date 2023/4/6 16:16
 */
public class TestDeadLock {
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void testLock1() {
        synchronized (lock1) {
            System.out.println("lock1=================");
            synchronized (lock2) {
                System.out.println("lock2=========================");
            }
        }
    }

    public static void testLock2() {
        synchronized (lock2) {
            System.out.println("lock1++++++++++++++++");
            synchronized (lock1) {
                System.out.println("lock2+++++++++++++++++++");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            testLock1();
        }).start();
      //  TimeUnit.SECONDS.sleep(1);
        testLock2();
    }



}
