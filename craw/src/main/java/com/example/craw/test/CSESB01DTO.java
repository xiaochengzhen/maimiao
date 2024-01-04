package com.example.craw.test;

import lombok.Data;

import java.util.List;

/**
 * @description 
 * @author xiaobo
 * @date 2023/12/11 9:53
 */
@Data
public class CSESB01DTO {
    //开始
    private ControlRecordStart controlRecordStart;

    //详情
    private List<DetailRecord> detailRecord;

    @Data
    public static class ControlRecordStart{

        /**
         * “0” = control header
         */
        @RuleAnnotation(value = "X(1)")
        private String recordType;
        /**
         * participantID
         */
        @RuleAnnotation(value = "X(6)")
        private String participantID;
        /**
         * “CSESB01”
         */
        @RuleAnnotation(value = "X(7)")
        private String reportID;
        /**
         * “DAILY STK BAL”
         */
        @RuleAnnotation(value = "X(15)")
        private String reportName;
        /**
         * HKMK
         */
        @RuleAnnotation(value = "X(4)")
        private String marketCode;
        /**
         * YYYYMMDD
         */
        @RuleAnnotation(value = "9(8)")
        private String cCASSDate;

        /**
         * Spaces
         */
        @RuleAnnotation(value = "X(54)")
        private String filler1;
        /**
         * Reserved for system use
         */
        @RuleAnnotation(value = "X(3)")
        private String filler2;
    }

    @Data
    public static class DetailRecord{

        /**
         * “1” = detail records of account balances
         */
        @RuleAnnotation(value = "X(1)")
        private String recordType;

        /**
         * base symbol
         */
        @RuleAnnotation(value = "9(5)")
        private String stockCode;

        /**
         * isin
         */
        @RuleAnnotation(value = "X(12)")
        private String isin;

        /**
         * Currency code
         */
        @RuleAnnotation(value = "X(3)")
        private String currencyCode ;

        /**
         *   Padded with leading blank space if account number contains less than 8 digits
         */
        @RuleAnnotation(value = "X(8)")
        private String stockAccountCode;

        /**
         *  Stock account balance
         */
        @RuleAnnotation(value = "9(15)")
        private String stockAccountBalance ;
        /**
         *  Blank = 0 or positive balance"-" = negative balance
         */
        @RuleAnnotation(value = "X(1)")
        private String signOfStockBalance;
        /**
         * Stock account value
         */
        @RuleAnnotation(value = "9(16)V9(2)")
        private String  stockAccountValue  ;
        /**
         * Blank = 0 or positive value"-" = negative value
         */
        @RuleAnnotation(value = "X(1)")
        private String signOfStockValue;

        /**
         * Sum of stock codes, Balances and values
         */
        @RuleAnnotation(value = "9(18)")
        private String recordchecksum ;

        /**
         * “*”=Stock   conversion without   parallel tradingBlank=others
         */
        @RuleAnnotation(value = "X(1)")
        private String cAConversionindicator ;

        /**
         * Spaces
         */
        @RuleAnnotation(value = "X(12)")
        private String filler1 ;

        /**
         * Reserved for system use
         */
        @RuleAnnotation(value = "X(3)")
        private String filler2 ;


    }
}
