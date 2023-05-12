package com.example.javabase.concurrent.reentrantlock;

import java.util.concurrent.Exchanger;

/**
 * @author xiaobo
 * @description
 * @date 2023/4/7 11:07
 */
public class TestExchanger {

    private static Exchanger exchanger = new Exchanger();
    private static String goods = "电脑";
    private static String money = "$4000";

    public static void main(String[] args) {
        new Thread(() ->{
            Object exchange = null;
            try {
                exchange = exchanger.exchange(goods);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("付款"+exchange);
        }).start();


        new Thread(() ->{
            Object exchange = null;
            try {
                exchange = exchanger.exchange(money);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("买到"+exchange);
        }).start();
    }
}
