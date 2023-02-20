package com.example.javabase.function;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/12 14:22
 */
public class FunctionDemo {
    public static void main(String[] args) {
        functionString("100", s -> Integer.parseInt(s));
        functionString("100", Integer::parseInt);
        functionStringAndThen("200", Integer::parseInt, Integer::parseInt);
    }

    public  static void  functionString(String str, Function<String, Integer> function) {
        Integer apply = function.apply(str);
        System.out.println(apply);
    }

    public  static void  functionStringAndThen(String str, Function<String, Integer> function, Function<String, Integer> function2) {
        Integer apply1 = function2.apply(function.apply(str) + "");
        System.out.println(apply1);
        Map map = new HashMap<>();
        Set set = map.keySet();
        Collection values = map.values();
    }
}
