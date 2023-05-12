package com.example.javabase.concurrent.reentrantlock.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiaobo
 * @description
 * @date 2023/4/7 09:23
 */
public class TestCondition {

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    int count = 0;
    public static void main(String[] args) throws InterruptedException {
        TestCondition testCondition = new TestCondition();
        ConditionThread1 conditionThread1 = new ConditionThread1(testCondition);
        conditionThread1.start();

        for (int i = 0; i < 10; i++) {
            ConditionThread conditionThread = new ConditionThread(testCondition);
            conditionThread.start();
            TimeUnit.SECONDS.sleep(1);
        }

    }

    public void testAwait() {
        lock.lock();
        try {
            while (count < 5) {
                System.out.println("=========await start============"+count);
                condition.await();
                System.out.println("=========await end============"+count);
            }
            System.out.println("===========await function end=================="+count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void testSingnal() {
        lock.lock();
        try {
            count++;
            System.out.println("=========signal start============");
            condition.signalAll();
            System.out.println("=========signal end============");
        }finally {
            lock.unlock();
        }
    }

    public static class ConditionThread1 extends Thread{
        private TestCondition testCondition;

        public ConditionThread1(TestCondition testCondition) {
            this.testCondition = testCondition;
        }

        @Override
        public void run() {
            testCondition.testAwait();
        }
    }


    public static class ConditionThread extends Thread{
        private TestCondition testCondition;

        public ConditionThread(TestCondition testCondition) {
            this.testCondition = testCondition;
        }

        @Override
        public void run() {
            testCondition.testSingnal();
        }
    }


}
