package com.example.javabase.controller;

import com.example.javabase.service.CopyService;
import com.example.javabase.tem.StudentTem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @Autowired
    private StudentTem studentTem;

    @GetMapping("/test1")
    public void test() {
        studentTem.student();
    }

    public static void main(String[] args) {
        test1();
    }
    public static void  test1() {
        System.out.println("==========");
        new Thread(()->{
            int i = 1/0;
        }).start();
        System.out.println("+++++++++++++");
    }

    @Autowired
    private CopyService copyService;

    @GetMapping("/test2")
    public void test2() {
        copyService.test();
    }
}
