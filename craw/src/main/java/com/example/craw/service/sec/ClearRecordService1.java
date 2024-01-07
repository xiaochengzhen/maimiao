package com.example.craw.service.sec;

import com.example.craw.mapper.ClearRecordMapper;
import com.example.craw.model.ClearRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * @description 
 * @author xiaobo
 * @date 2023/12/20 10:27
 */
@Slf4j
@Service
public class ClearRecordService1 {
    @Autowired
    private ClearRecordMapper clearRecordMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public boolean financingInterestCal(String clearingDate) {
        // 利息清算
        // 查询清算记录
        ClearRecord clearRecord = clearRecordMapper.selectByclearingDate(clearingDate);
        // 融资计息状态为已完成不做处理
        if (clearRecord.getFinancingInterestCalculationStatus() == 2) {
            log.info("融资计息状态为已完成不做处理");
            return false;
        }
        // 更新融资计息状态
        if (clearRecordMapper.updateFinancingInterestStatus(clearingDate, 0, 1) == 0) {
            throw new RuntimeException("");
        }
        try {
            log.info("融资计息开始");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            // 更新融资计息状态
            clearRecordMapper.updateFinancingInterestStatus(clearingDate, 1, 2);
        } catch (Exception e) {
            log.error("融资计息异常", e);
            throw new RuntimeException("");
        }
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public boolean stockBorrowedInterestCal(String clearingDate) {
            // 利息清算
            // 查询清算记录
            ClearRecord clearRecord = clearRecordMapper.selectByclearingDate(clearingDate);
            // 融资计息状态为已完成不做处理
            if (clearRecord.getStockBorrowInterestCalculationStatus() == 2) {
                log.info("融资计息状态为已完成不做处理");
                return false;
            }
            // 更新融资计息状态
            if (clearRecordMapper.updateStockBorrowInterestStatus(clearingDate, 0, 1) == 0) {
                throw new RuntimeException("");
            }
            try {
                log.info("融资计息开始");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                // 更新融资计息状态
                clearRecordMapper.updateStockBorrowInterestStatus(clearingDate, 1, 2);
            } catch (Exception e) {
                log.error("融资计息异常", e);
                throw new RuntimeException("");
            }
                return true;
    }


}
