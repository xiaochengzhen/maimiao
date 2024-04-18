package com.example.zookeeperdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @description 
 * @author xiaobo
 * @date 2024/3/14 19:14
 */
@Component
public class TestA extends TestCycle{

    @Override
    protected void test() {

    }

    public static void main(String[] args) {
        System.out.println(System.lineSeparator());
    }
}
