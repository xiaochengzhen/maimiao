package com.example.craw.test;

import lombok.Data;

import java.util.List;

/**
 * @description 
 * @author xiaobo
 * @date 2023/12/11 9:53
 */
@Data
public class CCLTN05DTO {
    //开始
    private ControlRecordStart controlRecordStart;

    //详情
    private List<DetailRecord> detailRecords;

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
         * “CCLTN05”
         */
        @RuleAnnotation(value = "X(7)")
        private String reportID;
        /**
         * “FCS”
         */
        @RuleAnnotation(value = "X(15)")
        private String reportFileName;
        /**
         * HKMK
         */
        @RuleAnnotation(value = "X(4)")
        private String marketCode;
        /**
         * YYYYMMDD
         */
        @RuleAnnotation(value = "9(8)")
        private String tradeDate;
        /**
         * YYYYMMDD
         */
        @RuleAnnotation(value = "9(8)")
        private String settlementDate;
        /**
         * Spaces
         */
        @RuleAnnotation(value = "X(91)")
        private String filler1;
        /**
         * Reserved for system use
         */
     //   @RuleAnnotation(value = "X(3)")
        private String filler2;
    }

    @Data
    public static class DetailRecord{

        /**
         * detail records of trades
         */
        @RuleAnnotation(value = "X(1)")
        private String recordType;
        /**
         * “1” = CCASS trades“2” = non-CCASS trades
         */
        @RuleAnnotation(value = "9(1)")
        private String tradeIndicator;
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
         * For non-CCASS trades, always blankFor CCASS trades,“1” =  to be netted by HKSCC “2” = isolated trades
         */
        @RuleAnnotation(value = "X(1)")
        private String isolationIndicator;
        /**
         * “H”=isolated   trades,   isolated   by HKSCC“B”=isolated trades, buy-in“I”=isolated   trades,   isolated   by Exchange ParticipantBlank   for   non-CCASS   trades,   and CCASS netted trades
         */
        @RuleAnnotation(value = "X(1)")
        private String reasonOfIsolation;
        /**
         * Blank for non-CCASS trades
         */
        @RuleAnnotation(value = "X(9)")
        private String settlementPositionNumber;
        /**
         * B, S
         */
        @RuleAnnotation(value = "X(1)")
        private String buySellIndicator;
        /**
         * HHMM
         */
        @RuleAnnotation(value = "9(4)")
        private String tradeTime;
        /**
         * tradeReference
         */
        @RuleAnnotation(value = "9(16)")
        private String tradeReference;
        /**
         * Broker number of Exchange Participant
         */
        @RuleAnnotation(value = "9(4)")
        private String brokerNumberOfExchangeParticipant;
        /**
         * Counterparty broker number
         */
        @RuleAnnotation(value = "9(4)")
        private String counterpartyBrokerNumber;
        /**
         * Clearing Participant ID of counterparty
         */
        @RuleAnnotation(value = "X(6)")
        private String clearingParticipantIDOfCounterparty;
        /**
         * Trade quantity
         */
        @RuleAnnotation(value = "9(11)")
        private String tradeQuantity;
        /**
         * HHMM
         */
        @RuleAnnotation(value = "9(5)V9(3)")
        private String tradePrice;
        /**
         * Trade value
         */
        @RuleAnnotation(value = "9(11)V9(2)")
        private String tradeValue;
        /**
         * Currency code
         */
        @RuleAnnotation(value = "X(3)")
        private String currencyCode;
        /**
         * “A”=automatched“E”=semi-automatic special lot “M”=manual  (price  within  normal range)“O”=semi-automatic odd lot“Q”=special lot“P”=odd lot“R”=previous day“S”=manual  (price  outside  normal range or not on spread)“T”=option exercise“V”=overseas“U”=auction matching
         */
        @RuleAnnotation(value = "X(1)")
        private String tradingMethod;
        /**
         *   Blank for non-direct“X” for direct
         */
        @RuleAnnotation(value = "X(1)")
        private String directIndicator;
        /**
         * Charges 9(5)V9(2) Zero for non-CCASS trades
         */
        @RuleAnnotation(value = "9(5)V9(2)")
        private String charges;
        /**
         * Zero    for    non-debt    trades,    non-CCASS trades
         */
        @RuleAnnotation(value = "9(11)V9(2)")
        private String accruedInterest;
        /**
         * Blank= 0 or positive value“-”= negative value
         */
        @RuleAnnotation(value = "X(1)")
        private String signOfAccruedInterest;
        /**
         * For Seller:N= Non ShortsellY = ShortsellM=Market MakerA=Index ArbitrageF=Stock Futures  Hedging TransactionBlank = noneFor Buyer:N=Non ShortsellC=Shortsell Cover  Blank = none
         */
        @RuleAnnotation(value = "X(1)")
        private String shortsellIndicator;
        /**
         * Blank=none“A”=agency“I”     = institutional investor“P”=principal“R”=registered trader
         */
        @RuleAnnotation(value = "X(1)")
        private String originIndicator;
        /**
         * Blank=none“E”=hedge“N”=non-hedge“L”=hedge liquidation
         */
        @RuleAnnotation(value = "X(1)")
        private String hedgeIndicator;
        /**
         * Sum  of  stock  code,  trade  quantity, trade  price,  trade  value  and  accrued interest
         */
        @RuleAnnotation(value = "9(14)")
        private String recordchecksum;
        /**
         * Reserved for system use
         */
      //  @RuleAnnotation(value = "X(3)")
        private String filler;


    }
}
