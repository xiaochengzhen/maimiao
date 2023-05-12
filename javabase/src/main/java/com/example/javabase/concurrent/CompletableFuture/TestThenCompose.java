package com.example.javabase.concurrent.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author xiaobo
 * @description 组合转换（前一个结果作为参数）
 * @date 2023/4/6 15:11
 */
public class TestThenCompose {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("1");
            return "1";
        }).thenCompose(i -> CompletableFuture.supplyAsync(() -> {
            System.out.println("2");
            return i + "222";
        }));
        String s = stringCompletableFuture.get();
        System.out.println(s);
    }
}
