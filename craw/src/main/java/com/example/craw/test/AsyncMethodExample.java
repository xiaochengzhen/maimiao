package com.example.craw.test;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AsyncMethodExample {

    private final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        AsyncMethodExample example = new AsyncMethodExample();
        example.testAsyncMethod();
    }

    public void testAsyncMethod() {
        // 异步执行 dosomething 方法
        CompletableFuture<Void> asyncTask = CompletableFuture.runAsync(() -> {
            try {
                // 尝试加锁，如果失败，执行回调函数提供提示
                if (!lock.tryLock(3, TimeUnit.SECONDS)) {
                    handleLockFailure();
                    return;
                }

                dosomething();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                lock.unlock();
            }
        });

        // 继续执行其他业务逻辑
        System.out.println("Other business logic...");

        // 注册异步任务完成后的回调函数，可以在这里向用户提供提示
        asyncTask.thenRun(() -> System.out.println("Async Task completed"));
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void dosomething() {
        // 模拟异步操作
        try {
            TimeUnit.SECONDS.sleep(5);
            System.out.println("Async Task is running");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void handleLockFailure() {
        // 在这里提供加锁失败的提示，可以返回给用户或记录日志等
        System.out.println("Lock acquisition failed. Please try again later.");
    }
}
