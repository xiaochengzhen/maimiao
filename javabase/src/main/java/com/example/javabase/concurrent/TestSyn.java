package com.example.javabase.concurrent;

import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * @author xiaobo
 * @description
 * @date 2023/4/4 10:21
 */
@Data
public class TestSyn {
    public int count = 0;
    Object object = new Object();

    public void add1() {
        synchronized (this) {
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        }
    }

    public synchronized void add2() {
      //  synchronized (object) {
            for (int i = 0; i < 10000; i++) {
                count++;
            }
    //    }
    }

    public static void main(String[] args) throws InterruptedException {
        TestSyn testSyn = new TestSyn();
        SynThread1 synThread1 = new SynThread1(testSyn);
        SynThread2 synThread2 = new SynThread2(testSyn);
        synThread1.start();
        synThread2.start();
        TimeUnit.SECONDS.sleep(1);
        int count = testSyn.getCount();
        System.out.println(count);

    }

    private static class SynThread1 extends Thread{
        private TestSyn testSyn;

        public SynThread1(TestSyn testSyn) {
            this.testSyn = testSyn;
        }
        @Override
        public void run() {
            testSyn.add1();
        }
    }

    private static class SynThread2 extends  Thread{
        private TestSyn testSyn;

        public SynThread2(TestSyn testSyn) {
            this.testSyn = testSyn;
        }
        @Override
        public void run() {
            testSyn.add2();
        }
    }
}
