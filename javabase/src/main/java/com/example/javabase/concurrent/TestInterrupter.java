package com.example.javabase.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author xiaobo
 * @description
 * @date 2023/4/4 09:51
 */
public class TestInterrupter {

    public static void main(String[] args) throws InterruptedException {
        TestThreadInte testThreadInte = new TestThreadInte();
        testThreadInte.start();
       // testThreadInte.start();
        TimeUnit.SECONDS.sleep(1);
        testThreadInte.interrupt();
    }

    public static class TestThreadInte extends Thread{
        @Override
        public void run() {
           // while(!interrupted()) {
            while(!isInterrupted()) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    interrupt();
                }
                System.out.println(currentThread().getName());
            }
            System.out.println("isInterrupted:"+isInterrupted());
        }
    }

}
