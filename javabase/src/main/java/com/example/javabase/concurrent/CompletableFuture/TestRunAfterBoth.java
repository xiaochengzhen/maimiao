package com.example.javabase.concurrent.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaobo
 * @description 运行后执行(1和2运行完成才运行3, 1、2顺序不固定)
 * @date 2023/4/6 14:42
 */
public class TestRunAfterBoth {

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1");
            return "1";
        }).runAfterBoth(CompletableFuture.supplyAsync(()->{
                    System.out.println("2");
                    return "2";
                }), ()-> System.out.println("3"));
        TimeUnit.SECONDS.sleep(3);
    }
}
