package com.example.javabase.function;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/10 11:08
 */
public class MyStringDemo{
    public static void main(String[] args) {
        userMyString((s,x,y)->s.substring(x, y));
        userMyString(String::substring);
    }

    private static void userMyString(MyString myString) {
        String s = myString.mySubString("helloworld", 5, 10);
        System.out.println(s);
    }

}
