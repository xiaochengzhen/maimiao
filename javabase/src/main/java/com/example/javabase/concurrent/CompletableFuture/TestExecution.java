package com.example.javabase.concurrent.CompletableFuture;

import co.paralleluniverse.common.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @description 
 * @author xiaobo
 * @date 2023/12/18 8:25
 */
public class TestExecution {

    /*public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<String> exceptionally = CompletableFuture.supplyAsync(() -> {
            if (1==1) {
                System.out.println("thread1="+ Thread.currentThread().getName());
                throw new RuntimeException("");
            }
            try {
                TimeUnit.MILLISECONDS.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "1";
        });
        exceptionally.exceptionally(e->{
            System.out.println("thread2="+ Thread.currentThread().getName());
            System.out.println(e.getCause());
            return "2";
        });
        System.out.println("thread3="+ Thread.currentThread().getName());
        String s = exceptionally.get(3, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(3);
        System.out.println(s);
    }*/


    public static void main(String[] args) {
        List<Integer> pageNumList = getPageNumList(10, 201);
        for (Integer integer : pageNumList) {
            System.out.println(integer);
        }
        System.out.println("====================");
        List<Integer> pageNumList2 = getPageNumList2(10, 201);
        for (Integer integer : pageNumList2) {
            System.out.println(integer);
        }
    }
    public static List<Integer> getPageNumList(Integer pageSize, Integer count) {
        List<Integer> list = new ArrayList<>();
        if (pageSize != null && count != null) {
            for (int i = 0; true; i++) {
                list.add(i*pageSize);
                count -= pageSize;
                if (count <= 0) {
                    break;
                }
            }
        }
        return list;
    }

    public static List<Integer> getPageNumList2(Integer pageSize, Integer count) {
        List<Integer> list = new ArrayList<>();
        if (pageSize != null && count != null) {
            for (int i = 0; i <= count/pageSize; i++) {
                list.add(i*pageSize);
            }
        }
        return list;
    }
}
