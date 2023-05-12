package com.example.javabase.concurrent.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author xiaobo
 * @description 处理结果
 * @date 2023/4/6 14:38
 */
public class TestHandle {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> handle = CompletableFuture.supplyAsync(() -> {
            if(1==1) {
                throw new RuntimeException("error");
            }
            return "1";
        }).handle((s, t) -> {
            System.out.println(t);
            if (t != null) {
                return "ttt";
            }
            return s;
        });
        String o = handle.get();
        System.out.println(o);

    }
}
