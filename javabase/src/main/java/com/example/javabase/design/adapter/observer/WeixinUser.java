package com.example.javabase.design.adapter.observer;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/14 15:50
 */
public class WeixinUser implements Observer{
    public WeixinUser(String name) {
        this.name = name;
    }

    private String name;
    @Override
    public void update(String message) {
        System.out.println(name+message);
    }
}
