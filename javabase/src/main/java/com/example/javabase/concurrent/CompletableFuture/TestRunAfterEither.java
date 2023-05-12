package com.example.javabase.concurrent.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaobo
 * @description 取最快执行后执行
 * @date 2023/4/6 14:54
 */
public class TestRunAfterEither {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1");
            return "1";
        }).runAfterEitherAsync(CompletableFuture.supplyAsync(()->{
            System.out.println("2");
            return "2";
        }), ()-> System.out.println("3"));

        TimeUnit.SECONDS.sleep(3);
    }
}
