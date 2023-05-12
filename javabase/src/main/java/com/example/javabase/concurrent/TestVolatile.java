package com.example.javabase.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author xiaobo
 * @description
 * @date 2023/4/4 10:51
 */
public class TestVolatile {

    private static volatile boolean b = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
           while (b) {
              // System.out.println("111");
               /*try {
                   TimeUnit.SECONDS.sleep(1);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }*/
           }
           System.out.println("==============");
        }).start();
        TimeUnit.SECONDS.sleep(1);
        b = false;
    }
}
