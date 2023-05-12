package com.example.javabase.converter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/10 15:29
 */
@RestController
public class TestConverter {

    @GetMapping("/test")
    public void test(User user) {
        System.out.println(user.toString());
    }
}
