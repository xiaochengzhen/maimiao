package com.example.mybatisplusdemo.controller;

import com.example.mybatisplusdemo.service.impl.TableUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TableUserController {

    @Autowired
    private TableUserServiceImpl tableUserServiceImpl;

    @GetMapping("testUser")
    public void testUser() {
        tableUserServiceImpl.testTran();
    }
}
