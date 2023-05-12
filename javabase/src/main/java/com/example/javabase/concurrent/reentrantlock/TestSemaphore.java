package com.example.javabase.concurrent.reentrantlock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaobo
 * @description
 * @date 2023/4/7 10:19
 */
public class TestSemaphore {

    private static Semaphore semaphore = new Semaphore(2);
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        for (int i = 0; i <10; i++) {
            executorService.submit(()->{
                producter2();
            });
        }
    }

    public static void producter() {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName()+"============执行任务=============");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            semaphore.release();
            System.out.println(Thread.currentThread().getName()+"============释放任务=============");
        }

    }

    public static void producter2() {
        try {
            if (semaphore.tryAcquire()) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "============执行任务=============");
            } else {
                System.out.println(Thread.currentThread().getName() + "被流控了");
            }
        } finally {
            semaphore.release();
            System.out.println(Thread.currentThread().getName()+"============释放任务=============");
        }
    }
}
