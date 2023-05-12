package com.example.javabase.concurrent.CompletableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaobo
 * @description
 * @date 2023/4/6 13:41
 */
public class TestCompletableFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(()->{
            Random random = new Random(3);
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("c1 完成");
            return "c1";
        });

        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(()->{
            Random random = new Random(3);
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("c2 完成");
            return "c2";
        });

        CompletableFuture<String> completableFuture3= CompletableFuture.supplyAsync(()->{
            Random random = new Random(3);
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("c3 完成");
            return "c3";
        });

        CompletableFuture.allOf(completableFuture1, completableFuture2, completableFuture3)
                .thenRun(()->{
                    System.out.println("=============");
                });
        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.anyOf(completableFuture1, completableFuture2, completableFuture3);
        String str = (String) objectCompletableFuture.get();
        System.out.println(str);
        TimeUnit.SECONDS.sleep(10);
    }
}
