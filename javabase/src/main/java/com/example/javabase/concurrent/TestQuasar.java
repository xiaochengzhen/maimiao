package com.example.javabase.concurrent;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.Strand;
import org.springframework.util.StopWatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author xiaobo
 * @description
 * @date 2023/4/4 09:02
 */
public class TestQuasar {
    public static void main(String[] args) {
      //  testPool();
        testQuasar();
    }

    public static void testPool() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        CountDownLatch countDownLatch = new CountDownLatch(10000);
        ExecutorService executorService = Executors.newFixedThreadPool(600);
        IntStream.range(0,10000).forEach(i->executorService.submit(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }));

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        double totalTimeSeconds = stopWatch.getTotalTimeSeconds();
        System.out.println(totalTimeSeconds);
        executorService.shutdown();
    }

    public static void testQuasar() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        CountDownLatch countDownLatch = new CountDownLatch(10000);
        IntStream.range(0,10000).forEach(i-> new Fiber(){
            @Override
            protected String run() throws SuspendExecution, InterruptedException {
                try {
                    Strand.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                return "aa";
            }
        }.start());
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        double totalTimeSeconds = stopWatch.getTotalTimeSeconds();
        System.out.println(totalTimeSeconds);
    }
}
