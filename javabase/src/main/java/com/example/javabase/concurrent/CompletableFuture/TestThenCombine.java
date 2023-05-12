package com.example.javabase.concurrent.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author xiaobo
 * @description 组合转换类（前面所有结果作为参数）
 * @date 2023/4/6 15:07
 */
public class TestThenCombine {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("21");
            return "1";
        }).thenCombine(CompletableFuture.supplyAsync(()->{
                    System.out.println("2");
            return "2";
        }), (r1, r2) -> r1 + r2);
        String s = stringCompletableFuture.get();
        System.out.println(s);
    }
}
