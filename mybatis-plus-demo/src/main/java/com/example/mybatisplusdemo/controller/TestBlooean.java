package com.example.mybatisplusdemo.controller;/**
 * @description
 * @author xiaobo
 * @date 2023/4/27 07:28
 */

import com.example.mybatisplusdemo.model.TestB;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description
 * @author xiaobo
 * @date 2023/4/27 7:28
 */
@RestController
public class TestBlooean {

    @PostMapping("/testB")
    public void test(@RequestBody TestB testB) {
        System.out.println(testB.toString());
    }

    @GetMapping("/testB")
    public void testGet(TestB testB) {
        System.out.println(testB.toString());
    }
}
