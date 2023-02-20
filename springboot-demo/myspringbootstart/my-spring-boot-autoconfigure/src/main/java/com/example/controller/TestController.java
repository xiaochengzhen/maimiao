package com.example.controller;
import com.example.model.Properies;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/14 10:39
 */
@RestController
@RequestMapping("/test")
public class TestController {

    Properies properies;

    public TestController(Properies properies) {
        this.properies = properies;
    }

    @RequestMapping("/index")
    public String index() {
        return properies.getName()+"欢迎";
    }
}
