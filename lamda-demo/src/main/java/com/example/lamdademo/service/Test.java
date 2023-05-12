package com.example.lamdademo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * @author xiaobo
 * @description
 * @date 2023/2/23 16:25
 */
public class Test {

    public static void main(String[] args) {
        int i = workaroundSingleThread();
        System.out.println(i);
        Test2 test2 = ()->{
            return "aa";
        };
        String s = test2.testString();
        System.out.println(s);

        Test test = new Test();
        Test2 test21 = test::testString1ss;
        String s1 = test21.testString();
        System.out.println(s1);


        Test test1 = new Test();
        Test3 test3 = Test::testString1ss;
        String s2 = test3.testString(test1);
        System.out.println(s1);

    }

    public static void add() {
        System.out.println("add");
    }

    public String testString1ss() {
        return "dddd";
    }

    public static int workaroundSingleThread() {
        int[] holder = new int[] { 2 };
        IntStream sums = IntStream
                .of(1, 2, 3)
                .map(val -> val + holder[0]);
        int sum = sums.sum();
        System.out.println(sum);
        holder[0] = 0;
        return sums.sum();
    }
}
