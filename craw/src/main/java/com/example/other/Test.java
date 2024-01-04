package com.example.other;

import java.util.HashMap;
import java.util.Map;

/**
 * @description 
 * @author xiaobo
 * @date 2023/12/28 19:00
 */
public class Test {

    public static void main(String[] args) {
        Map<Student, String> map = new HashMap<>();


    }

    public void test1() {
        Map<String, Son> map = new HashMap<>();
        test1(map);
    }

    public <T extends Student> void test1(Map<String, T> map1) {
        Map<String, Student> map = new HashMap<>();
        map.put("", map1.get(""));
        Student student = map1.get("");
    }
}
