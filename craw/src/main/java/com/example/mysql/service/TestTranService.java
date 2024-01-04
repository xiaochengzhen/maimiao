package com.example.mysql.service;

import com.example.craw.mapper.ClearRecordMapper;
import com.example.craw.model.ClearRecord;
import com.example.mysql.mapper.SecAccountMapper;
import com.example.mysql.model.SecAccountDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

/**
 * @description 
 * @author xiaobo
 * @date 2023/12/21 14:51
 */
@Service
public class TestTranService {
    @Autowired
    private ClearRecordMapper clearRecordMapper;

    @Autowired
    private SecAccountMapper secAccountMapper;

    @Transactional
    public void test() {
        SecAccountDO secAccountDO = new SecAccountDO();
        secAccountDO.setRouteId(5);
        secAccountDO.setAccountType(1);
        secAccountDO.setAccountId("2");
        secAccountDO.setStatus(1);
        secAccountDO.setCreatedAt(new Date());
        secAccountDO.setUpdatedAt(new Date());
        secAccountDO.setId(1L);
        secAccountDO.setRouteId(2);
    //    secAccountMapper.updateByPrimaryKeySelective(secAccountDO);
      //  secAccountMapper.deleteByPrimaryKey(44L);
      //  secAccountMapper.selectByPrimaryKey(1L);
        System.out.println("");
        ClearRecord clearRecord = clearRecordMapper.selectByclearingDate("2023-12-19");

        System.out.println("");
    }

    public static void main(String[] args) {
        String ss = "the storage in the market is HK and the extraction method is CCASS";
        String replace = ss.toUpperCase(Locale.ROOT).replace(" ", "_");
        System.out.println(replace);

    }
}
