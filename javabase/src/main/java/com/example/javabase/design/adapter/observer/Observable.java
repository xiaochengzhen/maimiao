package com.example.javabase.design.adapter.observer;

/**
 * @author xiaobo
 * @description 被观察者
 * @date 2023/3/14 15:51
 */
public interface Observable {

    // 新增用户(新增观察者)
    void add(Observer observer);
    // 移除用户,或者说用户取消订阅(移除观察者)
    void del(Observer observer);
    // 发布 推送消息
    void notify(String message);
}
