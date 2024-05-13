package com.example.myproject;

import com.example.myspringboot.MySpringApplication;
import com.example.myspringboot.annotion.MySpringbootApplication;

@MySpringbootApplication
public class MyProjectApplication {

    public static void main(String[] args) {
        MySpringApplication.run(MyProjectApplication.class);
    }

}
