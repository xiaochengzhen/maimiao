package com.example.filterinterceptor.listener.spring;

import org.springframework.context.ApplicationEvent;

/**
 * @author xiaobo
 * @description 事件源
 * @date 2022/8/4 17:27
 */
public class TestEvent extends ApplicationEvent {
    public String ss;


    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }

    public TestEvent(String ss) {
        super(ss);
        this.ss=ss;
    }
}
