package com.example.ebangbase.service;

import com.example.ebangbase.mapper.TestMapper;
import com.example.ebangbase.model.TestDO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description 
 * @author xiaobo
 * @date 2024/3/26 11:21
 */
@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;

    @Bean
    public ObjectMapper objectMapper1() {
        return new ObjectMapper();
    }

    public Integer saveTest1(TestDO testDO){
        testMapper.insert1(testDO);
        System.out.println("======================1============================="+testDO.getId());
        return testDO.getId();
    }
    public Integer saveTest2(TestDO testDO){
        testMapper.insert2(testDO);
        System.out.println("=========================2=========================="+testDO.getId());
        return testDO.getId();
    }

    public Integer saveTest3(TestDO testDO){
        testMapper.insert3(testDO);
        System.out.println("==========================3========================="+testDO.getId());
        return testDO.getId();
    }

    public List<TestDO> getTestDO() {
        return testMapper.getTestDO();
    }

    @ConditionalOnBean
    @ConditionalOnMissingBean
    public void test() {
       InterfaceTest interfaceTest =  new InterfaceTest(){

            @Override
            public void test() {

            }
        };
    }
}
