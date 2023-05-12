package com.example.javabase.concurrent.CompletableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * @author xiaobo
 * @description 操作执行
 * @date 2023/4/6 15:14
 */
public class TestThenRun {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(()->{
            System.out.println("1");
            return "12";
        }).thenRun(()-> System.out.println("2222"));

    }
}
