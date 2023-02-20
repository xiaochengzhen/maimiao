package com.example.springretry;

import com.example.springretry.demo.SpringBootRetryTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SpringRetryApplicationTests {
    @Autowired
    private SpringBootRetryTest springBootRetryTest;
    @Test
    void contextLoads() {
    }

    @Test
    public void test1() {
        springBootRetryTest.call("123");
    }

}
