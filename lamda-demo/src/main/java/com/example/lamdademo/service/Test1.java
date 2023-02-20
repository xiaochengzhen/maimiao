package com.example.lamdademo.service;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/29 18:17
 */
public interface Test1 {

    int math(int a);
    default  int math1(int b){
        return 1;
    };
  //  int math2(int a, int b);
}
