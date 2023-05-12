package com.example.javabase.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static java.lang.Thread.currentThread;

/**
 * @author xiaobo
 * @description
 * @date 2023/4/4 09:43
 */
public class TestThread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadTest test = new ThreadTest();
        test.start();

        Thread thread = new Thread(new RunnableTest());
        thread.start();

        FutureTask task = new FutureTask(new CallAbleTest());
        Thread futureThread = new Thread(task);
        futureThread.start();
        Object o = task.get();
        System.out.println(o);

    }
    public static class ThreadTest extends Thread{

        @Override
        public void run() {
            System.out.println(currentThread().getName());
        }
    }

    public static class RunnableTest implements Runnable{

        @Override
        public void run() {
            System.out.println(currentThread().getName());
        }
    }

    public  static class CallAbleTest implements Callable{
        @Override
        public Object call() throws Exception {
            return "abc";
        }
    }
}
