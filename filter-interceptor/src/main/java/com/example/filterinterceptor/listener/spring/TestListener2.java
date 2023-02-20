package com.example.filterinterceptor.listener.spring;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author xiaobo
 * @description 监听器
 * @date 2022/8/4 17:28
 */
@Component
public class TestListener2 {

    @EventListener
    public void onApplicationEvent(TestEvent event) {
        System.out.println("监听2到事件发生："+event.getClass());
    }
}
