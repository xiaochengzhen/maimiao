package com.example.javabase.concurrent.waitnotify;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @author xiaobo
 * @description
 * @date 2023/4/6 09:39
 */
public class TestWaitAndNotify {
    public static Object object = new Object();
    public static Object object1 = new Object();
    public static volatile int count = 0;


    public static void main(String[] args) throws InterruptedException {
        TestWaitAndNotify testWaitAndNotify = new TestWaitAndNotify();
        TestWaitThread thread = new TestWaitThread(testWaitAndNotify);
        TestNotityThread testNotityThread = new TestNotityThread(testWaitAndNotify);
        thread.start();
        testNotityThread.start();
    }

    public void add() throws InterruptedException {
        synchronized (object1) {
            while(count<=5) {
                System.out.println("wait start count:  =========== "+count);
                object1.wait();
                System.out.println("wait end count:  =========== "+count);
            }
            System.out.println("function end count:  =========== "+count);
        }
    }

    public void sub() throws InterruptedException {
        synchronized (object1) {
            count++;
            object1.notifyAll();
            System.out.println("notify start count:  =========== "+count);
        }
    }

    public static class TestWaitThread extends Thread{

        public TestWaitThread(TestWaitAndNotify testWaitAndNotify) {
            this.testWaitAndNotify = testWaitAndNotify;
        }

        private  TestWaitAndNotify testWaitAndNotify;
        @SneakyThrows
        @Override
        public void run() {
            testWaitAndNotify.add();
        }
    }

    public static class TestNotityThread extends Thread{
        private  TestWaitAndNotify testWaitAndNotify;

        public TestNotityThread(TestWaitAndNotify testWaitAndNotify) {
            this.testWaitAndNotify = testWaitAndNotify;
        }

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i <10; i++) {
                testWaitAndNotify.sub();
                TimeUnit.SECONDS.sleep(1);
            }
        }
    }
}
