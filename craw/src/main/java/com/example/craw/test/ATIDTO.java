package com.example.craw.test;
import lombok.Data;
import java.util.List;

/**
 * @description 
 * @author xiaobo
 * @date 2023/12/8 16:42
 */
@Data
public class ATIDTO {

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
        private String recordType;
        /**
         * 每个自然日内不能重复的由我们指定的 4 位文件编号
         */
        @RuleAnnotation(value = "9(4)")
        private String fileIndicator;
        /**
         * 我们的 EP ID，暂时填入“123456”
         */
        @RuleAnnotation(value = "X(6)")
        private String participantID;
        /**
         * 为空，填充 8 个空格
         */
        @RuleAnnotation(value = "X(8)")
        private String senderBIC;
        /**
         * 留给我们的备注，可为空，填充 15 个空格
         */
        @RuleAnnotation(value = "X(15)")
        private String participantOwnFileReference;
        /**
         * YYYYMMDD ，上传的自然日
         */
        @RuleAnnotation(value = "9(8)")
        private String fileTransmissionDate;
        /**
         * 填入“ATI BATCH INPUT”
         */
        @RuleAnnotation(value = "X(15)")
        private String fileName;
        /**
         * 为空，填充 42 个空格
         */
        @RuleAnnotation(value = "X(42)")
        private String filler;
    }

    @Data
    public static class DetailRecord{
        /**
         * 1
         */
        @RuleAnnotation(value = "X(1)")
        private String recordType;
        /**
         * 填入港股股票代码，左边不足位数用 0 代替
         */
        @RuleAnnotation(value = "9(5)")
        private String stockCode;
        /**
         * 为空，填充 12 个空格
         */
        @RuleAnnotation(value = "X(12)")
        private String isin;
        /**
         * 我们在 CCASS 的渠道账号之一，暂时填入“1”，不足位数补充空格
         */
        @RuleAnnotation(value = "X(8)")
        private String fromACNumber;
        /**
         * 我们在 CCASS 的渠道账号之一，暂时填入“2”，不足位数补充空格
         */
        @RuleAnnotation(value = "X(8)")
        private String toACNumber;
        /**
         * 填入转移的持仓数量
         */
        @RuleAnnotation(value = "9(11)")
        private String transferQuantity;
        /**
         *留给我们的备注，可为空，填充 40 个空格
         */
        @RuleAnnotation(value = "X(40)")
        private String remarks;
        /**
         * Stock code 和 Transfer quantity 的数量之和，左边不足位数用 0 代替
         */
        @RuleAnnotation(value = "9(14)")
        private String recordChecksum;
    }

    @Data
    public static class ControlRecordEnd{
        /**
         * “2” = control trailer
         */
        @RuleAnnotation(value = "X(1)")
        private String recordType;
        /**
         * 第二部分的记录数量，左边不足位数用 0 代替
         */
        @RuleAnnotation(value = "9(4)")
        private String totalNumberOfDetailRecords;
        /**
         * 第二部分所有记录的 Stock code 相加，左边不足位数用 0 代替
         */
        @RuleAnnotation(value = "9(7)")
        private String sumOfAllStockCodes;
        /**
         * 第二部分所有记录的 Transfer quantities  相加，左边不足位数用 0 代替
         */
        @RuleAnnotation(value = "9(14)")
        private String sumOfAllTransferQuantities;
        /**
         * 第二部分所有记录的 Record checksum   相加，左边不足位数用 0 代替
         */
        @RuleAnnotation(value = "9(17)")
        private String sumOfAllRecordChecksums;

        /**
         * 为空，填充 56 个空格
         */
        @RuleAnnotation(value = "X(56)")
        private String filler;
    }


}
