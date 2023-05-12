package com.example.javabase.design.singleton;

/**
 * @author xiaobo
 * @description
 * @date 2023/2/24 09:40
 */
public class Singleton3 {

    private Singleton3() {};

    private static class Singleton3Son {
         private static Singleton3 singleton3 = new Singleton3();
    }

    public static Singleton3 getSingleton3() {
        return Singleton3Son.singleton3;
    }
}
