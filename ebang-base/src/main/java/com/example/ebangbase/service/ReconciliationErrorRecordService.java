package com.example.ebangbase.service;

import com.example.ebangbase.mapper.ReconciliationErrorRecordMapper;
import com.example.ebangbase.model.ReconciliationErrorRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

/**
 * @description 
 * @author xiaobo
 * @date 2024/5/23 14:31
 */
@Service
public class ReconciliationErrorRecordService {
    @Autowired
    private ReconciliationErrorRecordMapper reconciliationErrorRecordMapper;

    @Autowired
    private MessageSource messageSource;
    public List<ReconciliationErrorRecord> list() {
        List<ReconciliationErrorRecord> list = reconciliationErrorRecordMapper.list();
        return list;
    }

    public void test() {
        String message = messageSource.getMessage("code", null, Locale.SIMPLIFIED_CHINESE);

    }

}
