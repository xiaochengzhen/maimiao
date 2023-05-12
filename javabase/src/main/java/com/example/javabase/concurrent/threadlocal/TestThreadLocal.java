package com.example.javabase.concurrent.threadlocal;

/**
 * @author xiaobo
 * @description
 * @date 2023/4/6 15:27
 */
public class TestThreadLocal {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>(){
        @Override
        protected String initialValue() {
            return "s";
        }
    };
    public static void main(String[] args) {
        String s1 = threadLocal.get();
        System.out.println(s1);
        threadLocal.set("2");
        String s = threadLocal.get();
        System.out.println(s);
    }
}
