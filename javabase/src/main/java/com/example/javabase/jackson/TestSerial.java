package com.example.javabase.jackson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/10 16:11
 */
@RestController
public class TestSerial {
    @GetMapping("/serial")
    public void test(Maimiao maimiao) {
        System.out.println(maimiao.toString());
    }

    @PostMapping("/serial1")
    public void test1(@RequestBody Maimiao maimiao) {
        System.out.println(maimiao.toString());
    }
}
