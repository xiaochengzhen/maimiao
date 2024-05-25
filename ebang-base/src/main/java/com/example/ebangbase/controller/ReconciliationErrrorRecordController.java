package com.example.ebangbase.controller;

import com.example.ebangbase.model.ReconciliationErrorRecord;
import com.example.ebangbase.model.TestDO;
import com.example.ebangbase.model.TestList;
import com.example.ebangbase.service.ReconciliationErrorRecordService;
import com.example.ebangbase.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description 
 * @author xiaobo
 * @date 2024/3/26 11:22
 */
@RestController
public class ReconciliationErrrorRecordController {

    @Autowired
    private ReconciliationErrorRecordService reconciliationErrorRecordService;

    @RequestMapping("/error1")
    public void error() {
        reconciliationErrorRecordService.list();
    }

}
