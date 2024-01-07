package com.example.mybatisplusdemo.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/**
 * tb_tes
 * @author 
 */
@Data
public class TbTes implements Serializable {
    private Integer id;

    private String name;

    private String remark;

    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
       /* Map<String, String> map = new HashMap<>();
        String s = map.putIfAbsent("1", "2");
        String s1 = map.putIfAbsent("1", "3");
        System.out.println(s);
        System.out.println(s1);
        System.out.println(map.get("1"));*/
        test();
    }

    public static void test() {
        int[] strings = {1,2,3,4};
        int x = 0;
        int y = 0;
        for (int string : strings) {
         //   System.out.println(x++);
            test1(x++);
            System.out.println("================");
            System.out.println(++y);
        }
    }

    public static void test1(int x) {
        System.out.println("x==="+x);
    }
}