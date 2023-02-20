package com.example.javabase.function;

import java.util.function.Supplier;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/10 13:35
 */
public class SupplierDemo {
    public static void main(String[] args) {
        String s = getSupplierString(()->"sss");
        Integer i = getSupplierInteger(()->666);
        System.out.println(s);
        System.out.println(i);
    }

    public static String getSupplierString(Supplier<String> supplier) {
        return supplier.get();
    }

    public static Integer getSupplierInteger(Supplier<Integer> supplier) {
        return supplier.get();
    }

}
