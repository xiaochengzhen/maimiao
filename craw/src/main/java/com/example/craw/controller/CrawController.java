package com.example.craw.controller;

import com.example.craw.service.CrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 
 * @author xiaobo
 * @date 2023/11/29 9:51
 */
@RestController
public class CrawController {

    @Autowired
    private CrawService crawService;

    @GetMapping("/craw")
    public void craw() {
        crawService.craw();
    }

}
