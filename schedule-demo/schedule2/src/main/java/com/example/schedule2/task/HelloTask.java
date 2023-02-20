package com.example.schedule2.task;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/4 10:44
 */
public class HelloTask implements Runnable{
    @Override
    public void run() {
        System.out.println("hello task");
    }
}
