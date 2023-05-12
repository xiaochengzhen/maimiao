package com.example.javabase.design.adapter.observer.guava;


import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/14 15:50
 */
public class WeixinUser {
    public WeixinUser(String name) {
        this.name = name;
    }

    private String name;

    @Subscribe
    public void getMessage(Object arg) {
        System.out.println(this.name + "接收到消息:" + arg);
    }

    public static void main(String[] args) {
        EventBus bus = new EventBus();
        bus.register(new WeixinUser("张三"));
        bus.register(new WeixinUser("李四"));
        bus.register(new WeixinUser("王五"));
        bus.post("我修改了，要发布信息了");
    }
}
