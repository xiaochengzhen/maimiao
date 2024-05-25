package com.example.openfeign1.controller;

import com.example.openfeign1.feign.OrderFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 
 * @author xiaobo
 * @date 2024/5/25 17:29
 */
@RestController
public class OpenFeignController {

    @Autowired
    private OrderFeignService orderFeignService;

    @RequestMapping("/feign")
    public void feign() {
        System.out.println("================feign=================");
        String orderByUserIdAndCode2 = orderFeignService.order();
        System.out.println(orderByUserIdAndCode2);
    }
}
