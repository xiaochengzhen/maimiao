package com.example.other;

import org.springframework.scheduling.annotation.AsyncResult;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static java.lang.Thread.interrupted;

/**
 * @description 
 * @author xiaobo
 * @date 2023/12/28 19:00
 */
public class Test {

    public static void main(String[] args) {
        /*Student student = new Student();
        student.setAge(1);
        Class<Student> studentClass = Student.class;
        Field[] declaredFields = studentClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            try {
                Object o = declaredField.get(student);
                System.out.println(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        System.out.println(student.getAge());*/
        try {
            TimeUnit.SECONDS.sleep(1);
            future().get(2, TimeUnit.SECONDS);
        } catch (InterruptedException | NullPointerException | ExecutionException | TimeoutException e) {
            if (e instanceof InterruptedException) {
                System.out.println("1111111111");
            }
            Throwable cause = e.getCause();
            if (cause instanceof InterruptedException) {
                System.out.println("22222222");
            }
            Thread.currentThread().interrupt();
            interrupted();
            e.printStackTrace();
        }
    }

    public static Future future() {
        throw new NullPointerException();
       // return AsyncResult.forValue("");
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
