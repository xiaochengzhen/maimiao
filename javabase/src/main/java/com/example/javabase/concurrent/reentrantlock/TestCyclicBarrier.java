package com.example.javabase.concurrent.reentrantlock;

import java.util.concurrent.*;

/**
 * @author xiaobo
 * @description
 * @date 2023/4/7 11:02
 */
public class TestCyclicBarrier {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, ()->{
            System.out.println("==========出发了=============");

        });

        for (int i = 0; i < 5; i++) {
            final int id  = i+1;
            executorService.submit(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(id+"到达");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
