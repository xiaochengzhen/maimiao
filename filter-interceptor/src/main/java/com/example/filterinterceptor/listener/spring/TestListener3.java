package com.example.filterinterceptor.listener.spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author xiaobo
 * @description 监听器
 * @date 2022/8/4 17:28
 */
@Component
public class TestListener3 implements SmartApplicationListener {

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return false;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {

    }
}
