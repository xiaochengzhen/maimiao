package com.example.javabase.concurrent.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaobo
 * @description 组合消费 consumer ->accept
 * @date 2023/4/6 14:59
 */
public class TestThenAcceptBoth {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture.supplyAsync(()->{
            System.out.println("1");
            return "1";
        }).thenAcceptBoth(CompletableFuture.supplyAsync(()->{
                    System.out.println("2");
                    return "2";
                }),
                (r1,r2)->{
                    System.out.println(r1+r2);
                });
        TimeUnit.SECONDS.sleep(3);
    }
}
