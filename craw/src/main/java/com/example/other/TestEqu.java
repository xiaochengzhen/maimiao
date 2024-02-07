package com.example.other;
/**
 * @description 
 * @author xiaobo
 * @date 2024/1/9 9:54
 */
public class TestEqu {

    public static void main(String[] args) {
        Student student1 = new Student();
        Student student2 = new Student();
        student1.setAge(1);
        student2.setAge(1);
        System.out.println(student1.equals(student2));
    }
}
