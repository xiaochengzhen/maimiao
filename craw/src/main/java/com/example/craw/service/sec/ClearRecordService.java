package com.example.craw.service.sec;

import com.example.craw.mapper.ClearRecordMapper;
import com.example.craw.model.ClearRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description 
 * @author xiaobo
 * @date 2023/12/20 10:27
 */
@Slf4j
@Service
public class ClearRecordService {
    @Autowired
    private ClearRecordMapper clearRecordMapper;
    @Autowired
    private ClearRecordService1 clearRecordService1;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void test(String clearingDate) {
        ClearRecord clearRecord = clearRecordMapper.selectByclearingDate(clearingDate);
        if (clearRecord.getClearStatus() != 4) {
            log.info("清算状态不为待计息");
            return ;
        }
        try {
            log.info("利息清算开始");
            // 融资利息计息
            boolean financingInterest = clearRecordService1.financingInterestCal(clearingDate);
            // 融券利息计息
            boolean stockBorrowedInterest = clearRecordService1.stockBorrowedInterestCal(clearingDate);
            // 更新清算记录状态为待入账
            clearRecordMapper.updateClearStatus(clearingDate, 4, 1);
            log.info("清算计息正常结束");
        } catch (Exception e) {
            log.error("清算计息异常", e);
            throw new RuntimeException("");
        }
    }


}
