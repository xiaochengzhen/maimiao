package com.example.ebangbase.controller;

import com.example.ebangbase.model.TestDO;
import com.example.ebangbase.model.TestList;
import com.example.ebangbase.service.TestService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description 
 * @author xiaobo
 * @date 2024/3/26 11:22
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/test1")
    public Integer saveTest1() {
        TestDO testDO = new TestDO();
        testDO.setAge(1L);
        return testService.saveTest1(testDO);
    }

    @RequestMapping("/test2")
    public Integer saveTest2() {
        TestDO testDO = new TestDO();
        testDO.setAge(1L);
        return testService.saveTest2(testDO);
    }

    @RequestMapping("/test3")
    public Integer saveTest3() {
        TestDO testDO = new TestDO();
        testDO.setAge(1L);
        return testService.saveTest3(testDO);
    }

    @RequestMapping("/test4")
    public List<TestDO> test4() {
        return testService.getTestDO();
    }

    @PostMapping("/test5")
    public void test4(@RequestBody TestList testList) {
        System.out.println(testList.toString());
    }


}
