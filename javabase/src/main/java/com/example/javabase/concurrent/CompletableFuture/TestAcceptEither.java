package com.example.javabase.concurrent.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaobo
 * @description 取最快消费类
 * @date 2023/4/6 14:24
 */
public class TestAcceptEither {

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "1";
        }).acceptEither(CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "2";
        }), (s)-> System.out.println(s));

        TimeUnit.SECONDS.sleep(3);
    }
}
