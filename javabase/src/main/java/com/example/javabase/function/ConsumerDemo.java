package com.example.javabase.function;

import java.util.function.Consumer;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/10 14:49
 */
public class ConsumerDemo {
    public static void main(String[] args) {
        operatorString("abc", s-> System.out.println(s));
        operatorStringAndThen("abc", s-> System.out.println(s), s-> System.out.println(new StringBuilder(s).reverse()));
    }

    public static void operatorString(String name, Consumer<String> consumer) {
        consumer.accept(name);
    }

    public static void operatorStringAndThen(String name, Consumer<String> consumer1, Consumer<String> consumer2) {
        consumer1.andThen(consumer2).accept(name);
    }
}
