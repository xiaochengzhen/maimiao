package com.example.javabase.design.singleton;

/**
 * @author xiaobo
 * @description
 * @date 2023/2/24 09:34
 */
public class Singleton2 {

    private Singleton2() {};

    private volatile static Singleton2 singleton2;

    public static synchronized Singleton2 getSinteton2() {
        if (singleton2 == null){
            synchronized (Singleton2.class) {
                if (singleton2 == null){
                     singleton2 = new Singleton2();
                }
            }
        }
        return singleton2;
    }
}
