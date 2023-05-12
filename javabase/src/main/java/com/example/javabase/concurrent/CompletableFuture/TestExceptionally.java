package com.example.javabase.concurrent.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author xiaobo
 * @description 出现异常补偿
 * @date 2023/4/6 14:32
 */
public class TestExceptionally {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> exceptionally = CompletableFuture.supplyAsync(() -> {
            if (1==1) {
                throw new RuntimeException("");
            }
            return "1";
        }).exceptionally(e->{
            System.out.println(e.getCause());
            return "2";
        });
        String s = exceptionally.get();
        System.out.println(s);
    }
}
