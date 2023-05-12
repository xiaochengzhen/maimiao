package com.example.mybatisplusdemo.controller;

import com.example.mybatisplusdemo.model.TestDefaultValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/13 13:24
 */
@RestController
public class TestDefaultValueController {

    @PostMapping("/testDefault")
    public void testDefault(@RequestBody List<TestDefaultValue> testDefaultValue) {
        System.out.println(testDefaultValue);
    }
}
