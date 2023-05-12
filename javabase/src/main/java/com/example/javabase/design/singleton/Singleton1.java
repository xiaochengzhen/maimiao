package com.example.javabase.design.singleton;

/**
 * @author xiaobo
 * @description
 * @date 2023/2/24 09:33
 */
public class Singleton1 {
    private Singleton1() {

    }
    private static Singleton1 singleton = new Singleton1();

    public static Singleton1 getSingleton() {
        return singleton;
    }
}
