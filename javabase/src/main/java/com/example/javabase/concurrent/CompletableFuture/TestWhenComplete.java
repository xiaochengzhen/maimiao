package com.example.javabase.concurrent.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author xiaobo
 * @description 运行后记录结果
 * @date 2023/4/6 15:16
 */
public class TestWhenComplete {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> exceptionally = CompletableFuture.supplyAsync(() -> {
            System.out.println("1");
            if(1==1) {
                throw new RuntimeException("fail");
            }
            return "1";
        }).whenComplete((s, t) -> {
            if (t != null) {
                System.out.println("error");
            }
            System.out.println(s);
        }).exceptionally(e -> {
            if (e != null) {
                return "exce";
            }
            return "success";
        });
        String s = exceptionally.get();
        System.out.println(s);
    }
}
