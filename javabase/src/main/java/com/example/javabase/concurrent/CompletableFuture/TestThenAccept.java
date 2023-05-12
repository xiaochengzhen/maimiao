package com.example.javabase.concurrent.CompletableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * @author xiaobo
 * @description 消费类
 * @date 2023/4/6 14:57
 */
public class TestThenAccept {

    public static void main(String[] args) {
        CompletableFuture.supplyAsync(()->{
            System.out.println("1");
            return "1";
        }).thenAccept(s-> System.out.println(s));

    }
}
