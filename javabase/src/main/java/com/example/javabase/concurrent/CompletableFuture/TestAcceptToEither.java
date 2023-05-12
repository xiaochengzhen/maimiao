package com.example.javabase.concurrent.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaobo
 * @description 取最快消费类(有返回值)
 * @date 2023/4/6 14:24
 */
public class TestAcceptToEither {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "1";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "2";
        }), (s) -> s);
        String s = stringCompletableFuture.get();
        System.out.println(s);
        TimeUnit.SECONDS.sleep(3);
    }
}
