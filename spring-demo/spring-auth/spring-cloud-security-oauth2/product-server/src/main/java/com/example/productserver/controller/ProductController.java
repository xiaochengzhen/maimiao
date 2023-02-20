package com.example.productserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fox
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @RequestMapping("/selectProductInfoById")
    public String selectProductInfoById(long id) {

        return "获取产品信息成功";
    }
}
