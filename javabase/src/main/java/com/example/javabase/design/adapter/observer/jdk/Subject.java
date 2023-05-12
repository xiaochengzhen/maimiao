package com.example.javabase.design.adapter.observer.jdk;

import com.example.javabase.design.adapter.observer.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/14 15:52
 */
public class Subject extends Observable {
    List<Observer> list = new ArrayList<>();
    // 公众号的名字
    private String name;
    // 公众号发布消息
    public void notifyMessage(String message) {
        System.out.println(this.name + "公众号发布消息:" + message + "请关注用户留意接收!");
        super.setChanged();
        super.notifyObservers(message);
    }

    public static void main(String[] args) {
        Subject subject = new Subject();
        subject.addObserver(new WeixinUser("张三"));
        subject.addObserver(new WeixinUser("李四"));
        subject.addObserver(new WeixinUser("王五"));
        subject.notifyMessage("我要发布消息了啊");
    }


}
