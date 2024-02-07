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
public class CCLTN05DTO {
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
        private String reportID = "CCLTN05";
        /**
         * 为空，填充 8 个空格
         */
        @RuleAnnotation(value = "X(15)")
        private String reportFileName = "FCS";
        /**
         * 为空，填充 8 个空格
         */
        @RuleAnnotation(value = "X(4)")
        private String marketCode = "HKMK";
        /**
         * 留给我们的备注，可为空，填充 15 个空格
         */
        @RuleAnnotation(value = "9(8)")
        private String tradeDate;
        /**
         * 留给我们的备注，可为空，填充 15 个空格
         */
        @RuleAnnotation(value = "9(8)")
        private String settlementDate;
        /**
         * 为空，填充 42 个空格
         */
        @RuleAnnotation(value = "X(91)")
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
         * 1
         */
        @RuleAnnotation(value = "9(1)")
        private String indicator="1";
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
        @RuleAnnotation(value = "X(1)")
        private String isolationIndicator="1";
        /**
         * 为空，填充 12 个空格
         */
        @RuleAnnotation(value = "X(1)")
        private String reason="I";
        /**
         * 为空，填充 12 个空格
         */
        @RuleAnnotation(value = "X(9)")
        private String settlementPositionNumber="P40063659";
        /**
         * 为空，填充 12 个空格
         */
        @RuleAnnotation(value = "X(1)")
        private String sell;
        /**
         * 为空，填充 12 个空格
         */
        @RuleAnnotation(value = "9(4)")
        private String tradeTime;
        /**
         * 为空，填充 12 个空格
         */
        @RuleAnnotation(value = "9(16)")
        private String tradeReference="1001671509000000";
        /**
         * 为空，填充 12 个空格
         */
        @RuleAnnotation(value = "9(4)")
        private String participant="0001";
        /**
         * 为空，填充 12 个空格
         */
        @RuleAnnotation(value = "9(4)")
        private String countreparty="1705";
        /**
         * 为空，填充 12 个空格
         */
        @RuleAnnotation(value = "X(6)")
        private String clearingParticipant="1706C0";
        /**
         * 为空，填充 12 个空格
         */
        @RuleAnnotation(value = "9(11)")
        private String tradeQuantity;
        /**
         * 为空，填充 12 个空格
         */
        @RuleAnnotation(value = "9(5)V9(3)")
        private String tradePrice;
        /**
         * 为空，填充 12 个空格
         */
        @RuleAnnotation(value = "9(11)V9(2)")
        private String tradeValue;
        /**
         * 为空，填充 12 个空格
         */
        @RuleAnnotation(value = "X(3)")
        private String currencyCode="HKD";
        /**
         * 填入转移的持仓数量
         */
        @RuleAnnotation(value = "X(1)")
        private String tradingMethod="A";
        /**
         *留给我们的备注，可为空，填充 40 个空格
         */
        @RuleAnnotation(value = "X(1)")
        private String directIndicator="";
        /**
         *留给我们的备注，可为空，填充 40 个空格
         */
        @RuleAnnotation(value = "9(5)V9(2)")
        private String charges="0";
        /**
         *留给我们的备注，可为空，填充 40 个空格
         */
        @RuleAnnotation(value = "9(11)V9(2)")
        private String accruedInterest="0";
        /**
         *留给我们的备注，可为空，填充 40 个空格
         */
        @RuleAnnotation(value = "X(1)")
        private String sign="";
        /**
         *留给我们的备注，可为空，填充 40 个空格
         */
        @RuleAnnotation(value = "X(1)")
        private String shortSellIndicator="Y";
        /**
         *留给我们的备注，可为空，填充 40 个空格
         */
        @RuleAnnotation(value = "X(1)")
        private String originIndicator="A";
        /**
         *留给我们的备注，可为空，填充 40 个空格
         */
        @RuleAnnotation(value = "X(1)")
        private String hedgeIndicator="L";
        /**
         * Stock code 和 Transfer quantity 的数量之和，左边不足位数用 0 代替
         */
        @RuleAnnotation(value = "9(14)")
        private String recordChecksum;
        /**
         * 为空，填充 42 个空格
         */
        @RuleAnnotation(value = "X(3)")
        private String filler="";
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
        @RuleAnnotation(value = "9(10)")
        private String sumOfAllStockCodes;
        /**
         * 第二部分所有记录的 Stock code 相加，左边不足位数用 0 代替
         */
        @RuleAnnotation(value = "9(17)")
        private String sumOfAllTradeQuantities;
        /**
         * 第二部分所有记录的 Transfer quantities  相加，左边不足位数用 0 代替
         */
        @RuleAnnotation(value = "9(13)")
        private String sumOfAllTradePrices;
        /**
         * 第二部分所有记录的 Transfer quantities  相加，左边不足位数用 0 代替
         */
        @RuleAnnotation(value = "9(18)")
        private String sumOfAllTradeValues;
        /**
         * 第二部分所有记录的 Record checksum   相加，左边不足位数用 0 代替
         */
        @RuleAnnotation(value = "9(18)")
        private String sumOfAllAccruedInterest;
        /**
         * 第二部分所有记录的 Record checksum   相加，左边不足位数用 0 代替
         */
        @RuleAnnotation(value = "9(18)")
        private String sumOfAllRecordChecksums;
        /**
         * 为空，填充 56 个空格
         */
        @RuleAnnotation(value = "X(45)")
        private String filler1;
        /**
         * 为空，填充 56 个空格
         */
        @RuleAnnotation(value = "X(3)")
        private String filler2;
    }


}
