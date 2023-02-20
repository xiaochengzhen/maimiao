package com.example.filterinterceptor;

import com.example.filterinterceptor.listener.spring.TestEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

@SpringBootTest
class FilterInterceptorApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Test
    public void publisher() {
        TestEvent testEvent = new TestEvent("123");
        applicationEventPublisher.publishEvent(testEvent);
    }

}
