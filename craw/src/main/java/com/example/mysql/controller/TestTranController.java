package com.example.mysql.controller;

import com.example.mysql.service.TestTranService;
import org.apache.catalina.mbeans.MBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 
 * @author xiaobo
 * @date 2023/12/20 10:50
 */
@RestController
public class TestTranController {
    @Autowired
    private TestTranService testTranService;

    @GetMapping("/testt")
    public void test() {
        testTranService.test();
    }
}
