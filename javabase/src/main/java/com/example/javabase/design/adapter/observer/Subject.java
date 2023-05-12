package com.example.javabase.design.adapter.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/14 15:52
 */
public class Subject implements Observable{
    List<Observer> list = new ArrayList<>();
    @Override
    public void add(Observer observer) {
        list.add(observer);
    }

    @Override
    public void del(Observer observer) {
        list.remove(observer);
    }

    @Override
    public void notify(String message) {
        list.forEach(s->s.update(message));
    }

    public static void main(String[] args) {
        Subject subject = new Subject();
        subject.add(new WeixinUser("张三"));
        subject.add(new WeixinUser("李四"));
        subject.add(new WeixinUser("王五"));
        subject.notify("我要发布消息了啊");
    }
}
