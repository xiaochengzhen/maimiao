package com.example.javabase.fanxing;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/17 09:06
 */
@Data
public class MyClass2<T, D>{

    T value1;
    D value2;

    //<T> 不是代表返回值，只是生命这个是一个泛型方法，有一个参数是泛型，这个T和类型中的T无关，只和方法有关,所以尽量和类中的避免相同，不要再用T了
    public<T> void test(T t) {
        System.out.println(t);
    }
    public static void main(String[] args) {
        MyClass2<Integer, Double> myClass = new MyClass2();
        myClass.value1 = 1;
        myClass.value2 = 0.2;
        System.out.println(myClass);
        System.out.println(myClass.value1 instanceof Integer);
        System.out.println(myClass.value2 instanceof Double);
        System.out.println(myClass.value2 instanceof Object);

        myClass.test("123");
    }
}
