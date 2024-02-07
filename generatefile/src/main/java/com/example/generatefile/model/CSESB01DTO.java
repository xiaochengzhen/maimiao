package com.example.generatefile.model;

import com.example.generatefile.annotation.RuleAnnotation;
import lombok.Data;

import java.util.List;

/**
 * @description 
 * @author xiaobo
 * @date 2024/2/2 14:34
 */
@Data
public class CSESB01DTO {
    //开始
    private ControlRecordStart controlRecordStart;
    //详情
    private List<DetailRecord> detailRecords;
    //结束
    private ControlRecordEnd controlRecordEnd;
    @Data
    public static class ControlRecordStart{

        /**
         * 填入“0”
         */
        @RuleAnnotation(value = "X(1)")
        private String recordType = "0";
        /**
         * 我们的 EP ID，暂时填入“123456”
         */
        @RuleAnnotation(value = "X(6)")
        private String participantID = "123456";
        /**
         * 为空，填充 8 个空格
         */
        @RuleAnnotation(value = "X(7)")
        private String reportID = "CSESB01";
        /**
         * 为空，填充 8 个空格
         */
        @RuleAnnotation(value = "X(15)")
        private String reportName = "DAILY STK BAL";
        /**
         * 为空，填充 8 个空格
         */
        @RuleAnnotation(value = "X(4)")
        private String marketCode = "HKMK";
        /**
         * 留给我们的备注，可为空，填充 15 个空格
         */
        @RuleAnnotation(value = "9(8)")
        private String ccassDate;
        /**
         * 为空，填充 42 个空格
         */
        @RuleAnnotation(value = "X(54)")
        private String filler1="";
        /**
         * 为空，填充 42 个空格
         */
        @RuleAnnotation(value = "X(3)")
        private String filler2="";
    }


    @Data
    public static class DetailRecord{
        /**
        * 1
        */
        @RuleAnnotation(value = "X(1)")
        private String recordType="1";
        /**
         * 填入港股股票代码，左边不足位数用 0 代替
         */
        @RuleAnnotation(value = "9(5)")
        private String stockCode;
        /**
         * 为空，填充 12 个空格
         */
        @RuleAnnotation(value = "X(12)")
        private String isin="HK0001000014";
        /**
         * 为空，填充 12 个空格
         */
        @RuleAnnotation(value = "X(3)")
        private String currencyCode="HKD";
        /**
         * 1
         */
        @RuleAnnotation(value = "X(8)")
        private String stockAccountCode;
        /**
         * 1
         */
        @RuleAnnotation(value = "9(15)")
        private String stockAccountBalance;
        /**
         * 为空，填充 12 个空格
         */
        @RuleAnnotation(value = "X(1)")
        private String signOfStockBalance;
        /**
         * 为空，填充 12 个空格
         */
        @RuleAnnotation(value = "9(16)V9(2)")
        private String stockAccountValue="0";
        /**
         * 为空，填充 12 个空格
         */
        @RuleAnnotation(value = "X(1)")
        private String signOfStockValue="0";

        /**
         * Stock code 和 Transfer quantity 的数量之和，左边不足位数用 0 代替
         */
        @RuleAnnotation(value = "9(18)")
        private String recordChecksum;
        /**
         * Stock code 和 Transfer quantity 的数量之和，左边不足位数用 0 代替
         */
        @RuleAnnotation(value = "X(1)")
        private String caConversionIndicator="";
        /**
         * 为空，填充 42 个空格
         */
        @RuleAnnotation(value = "X(12)")
        private String filler1="";
        /**
         * 为空，填充 42 个空格
         */
        @RuleAnnotation(value = "X(3)")
        private String filler2="";
    }


    @Data
    public static class ControlRecordEnd{
        /**
         * “2” = control trailer
         */
        @RuleAnnotation(value = "X(1)")
        private String recordType="9";

        /**
         * 第二部分所有记录的 Stock code 相加，左边不足位数用 0 代替
         */
        @RuleAnnotation(value = "9(18)")
        private String sumOfAllStockBalances;
        /**
         * 第二部分所有记录的 Stock code 相加，左边不足位数用 0 代替
         */
        @RuleAnnotation(value = "9(18)")
        private String sumOfAllStockValues;
        /**
         * 第二部分所有记录的 Record checksum   相加，左边不足位数用 0 代替
         */
        @RuleAnnotation(value = "9(18)")
        private String sumOfAllRecordChecksums;
        /**
         * 为空，填充 56 个空格
         */
        @RuleAnnotation(value = "X(40)")
        private String filler1;
        /**
         * 为空，填充 56 个空格
         */
        @RuleAnnotation(value = "X(3)")
        private String filler2;
    }


}
