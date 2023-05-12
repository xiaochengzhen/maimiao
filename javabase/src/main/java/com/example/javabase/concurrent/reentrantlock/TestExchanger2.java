package com.example.javabase.concurrent.reentrantlock;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Exchanger;

/**
 * @author xiaobo
 * @description
 * @date 2023/4/7 11:19
 */
public class TestExchanger2 {
    private static ArrayBlockingQueue<String> arrayBlockingQueue1 = new ArrayBlockingQueue<>(5);
    private static ArrayBlockingQueue<String> arrayBlockingQueue2 = new ArrayBlockingQueue<>(5);
    private static Exchanger<ArrayBlockingQueue> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(()->{
            ArrayBlockingQueue currt = arrayBlockingQueue1;
            while (currt != null) {
                try {
                    String uuId = UUID.randomUUID().toString();
                    currt.add(uuId);
                } catch (Exception e) {
                    try {
                        currt = exchanger.exchange(currt);
                        System.out.println(Thread.currentThread().getName()+"队列满了， 换个空的");
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(()->{
            ArrayBlockingQueue currt = arrayBlockingQueue2;
            while (currt != null) {
                if (!currt.isEmpty()) {
                    Object poll = currt.poll();
                } else {
                    try {
                        currt = exchanger.exchange(currt);
                        System.out.println(Thread.currentThread().getName()+"队列空的， 换个慢的");
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
