package com.example.craw.controller;

import com.example.craw.service.SymbolGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description
 * @author xiaobo
 * @date 2023/11/29 9:51
 */
@RestController
public class SymbolGradeController {
    @Autowired
    private SymbolGradeService symbolGradeService;

    @GetMapping("/grade")
    public void grade() {
        symbolGradeService.craw(null);
    }


}
