package com.example.craw.controller;

import com.example.craw.service.sec.ClearRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 
 * @author xiaobo
 * @date 2023/12/20 10:50
 */
@RestController
public class ClearRecordController {
    @Autowired
    private ClearRecordService clearRecordService;

    @GetMapping("/clear")
    public void test() {
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                clearRecordService.test("2023-11-14");
            }).start();
        }
    }
}
