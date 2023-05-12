package com.example.javabase.concurrent.CompletableFuture;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author xiaobo
 * @description 变换类  Function-》apply
 * @date 2023/4/6 15:03
 */
public class TestThenApply {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("1");
            return "1";
        }).thenApply(s -> s+"2");
        String s = stringCompletableFuture.get();
        System.out.println(s);
    }
}
