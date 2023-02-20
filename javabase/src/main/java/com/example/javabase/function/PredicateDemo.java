package com.example.javabase.function;

import java.util.function.Predicate;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/12 13:58
 */
public class PredicateDemo {
    public static void main(String[] args) {
        boolean b1 = checkString("hello", s -> s.length() > 5);
        System.out.println(b1);

        boolean b2 = checkString("hello", s -> s.length() > 4, s -> s.length() > 8);
        System.out.println(b2);
    }

    private static boolean checkString(String  s, Predicate<String> predicate) {
        return predicate.test(s);
    }

    private static boolean checkString(String s, Predicate<String> predicate, Predicate<String> predicate2){
        return predicate.or(predicate2).test(s);
    }
}
