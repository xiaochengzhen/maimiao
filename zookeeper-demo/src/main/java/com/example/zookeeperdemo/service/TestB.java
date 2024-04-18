package com.example.zookeeperdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description 
 * @author xiaobo
 * @date 2024/3/14 19:15
 */
@Component
public class TestB extends TestCycle{
    @Autowired
    private TestA testA;

    @Override
    protected void test() {
        testA.test();
    }
}
