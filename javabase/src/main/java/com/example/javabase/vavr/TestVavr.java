package com.example.javabase.vavr;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.control.Try;
import org.junit.Test;

import java.util.List;
import java.util.function.Function;

/**
 * @author xiaobo
 * @description
 * @date 2022/8/31 17:13
 */
public class TestVavr {

    @Test
    public void test1() {
        Tuple2<String, Integer> java = Tuple.of("java", 8);
        String s = java._1;
        String s1 = java._1();
        Integer integer = java._2;
        System.out.println(s);
        System.out.println(s1);
        System.out.println(integer);

        Tuple2<String, Integer> of = Tuple.of("", 1);
        System.out.println(of._1);
        System.out.println(of._2);

        Tuple2<Object, Integer> of1 = Tuple.of(null, 1);
        System.out.println(of1._1);
        System.out.println(of1._2);
        System.out.println(of1._2);
    }

    @Test
    public void test2() {
        Tuple2<String, Integer> java = Tuple.of("java", 8);
        Tuple2<String, Integer> map = java.map(s -> s + "8", i -> i + 1);
        System.out.println(map._1);
        System.out.println(map._2);
    }

    @Test
    public void test3() {
        Tuple2<String, Integer> java = Tuple.of("java", 8);
        String apply = java.apply((k, v) -> k + v);
        System.out.println(apply);
    }

    @Test
    public  void test4() {
        Integer dividend = 10;
        Integer divisor = 2;
        Try<Integer> of = Try.of(() -> dividend / divisor);
        Integer orElse = of.getOrElse(4);
        System.out.println(orElse);
    }

    @Test
    public  void test5() {
        Function<Integer, String> function = i->i+"abc";
        String func = func(function, 1);
        System.out.println(func);
    }

    public static <T, R> R  func(Function<T, R> function, T i) {
        R apply = function.apply(i);
        return apply;
    }

    @Test
    public  void test6() {
        List<Integer> list = List.of(1, 2);
        list.add(4);
        List<Integer> list2 = List.of(1, 2, 3);
        list = list2;
        System.out.println(list);
    }

    @Test
    public  void test7() {
        io.vavr.collection.List<Integer> list = io.vavr.collection.List.of(1, 2);
        list = list
                .appendAll(List.of(3, 4))
                .appendAll(io.vavr.collection.List.of(1, 2))
                .append(7);
        System.out.println(list);

    }
}
