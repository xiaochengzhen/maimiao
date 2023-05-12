package com.example.javabase.fanxing;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/17 09:06
 */
@Data
public class MyClass <T>{

    T value1;
    T value2;

    public static void main(String[] args) {
        MyClass<Integer> myClass = new MyClass();
        myClass.value1 = 1;
      //  myClass.value2 = 0.2;
        System.out.println(myClass);
        System.out.println(myClass.value1 instanceof Integer);
      //  System.out.println(myClass.value2 instanceof Double);
        System.out.println(myClass.value2 instanceof Object);
      //  System.out.println(myClass.value2 instanceof BigDecimal);
    }
}
