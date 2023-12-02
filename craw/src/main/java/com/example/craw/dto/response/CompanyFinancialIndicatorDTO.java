package com.example.craw.dto.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description 
 * @author xiaobo
 * @date 2023/12/1 10:25
 */
@NoArgsConstructor
@Data
public class CompanyFinancialIndicatorDTO {


    @JSONField(name = "code")
    private Integer code;
    @JSONField(name = "message")
    private String message;
    @JSONField(name = "data")
    private DataDTO data;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        /**
         * 流动比率
         */
        @JSONField(name = "3")
        private CurrentRatio currentRatio;
        /**
         * 速动比率
         */
        @JSONField(name = "4")
        private QuickRatio quickRatio;
        /**
         * 毛利率
         */
        @JSONField(name = "21")
        private GrossMargin grossMargin;
        /**
         * 净利率
         */
        @JSONField(name = "22")
        private NetMargin netMargin;
        /**
         * 净资产收益率
         */
        @JSONField(name = "24")
        private Roe roe;
        /**
         * 总资产收益率
         */
        @JSONField(name = "25")
        private Roa roa ;
        /**
         * 每股收益
         */
        @JSONField(name = "80")
        private Eps eps;
        /**
         * 自由现金流
         */
        @JSONField(name = "81")
        private Fcf fcf;
        /**
         * 每股净资产
         */
        @JSONField(name = "82")
        private Bvps bvps;

        @NoArgsConstructor
        @Data
        public static class CurrentRatio {
            @JSONField(name = "levelThreeType")
            private Integer levelThreeType;
            @JSONField(name = "dataInfo")
            private List<DataInfoDTO> dataInfo;

            @NoArgsConstructor
            @Data
            public static class DataInfoDTO {
                @JSONField(name = "financialType")
                private String financialType;
                @JSONField(name = "year")
                private String year;
                @JSONField(name = "indicatorData")
                private BigDecimal indicatorData;
                @JSONField(name = "otherIndicator")
                private BigDecimal otherIndicator;
                @JSONField(name = "f10Type")
                private String f10Type;
                @JSONField(name = "endDate")
                private String endDate;
            }
        }

        @NoArgsConstructor
        @Data
        public static class QuickRatio {
            @JSONField(name = "levelThreeType")
            private Integer levelThreeType;
            @JSONField(name = "dataInfo")
            private List<DataInfoDTO> dataInfo;

            @NoArgsConstructor
            @Data
            public static class DataInfoDTO {
                @JSONField(name = "financialType")
                private String financialType;
                @JSONField(name = "year")
                private String year;
                @JSONField(name = "indicatorData")
                private BigDecimal indicatorData;
                @JSONField(name = "otherIndicator")
                private BigDecimal otherIndicator;
                @JSONField(name = "f10Type")
                private String f10Type;
                @JSONField(name = "endDate")
                private String endDate;
            }
        }

        @NoArgsConstructor
        @Data
        public static class GrossMargin {
            @JSONField(name = "levelThreeType")
            private Integer levelThreeType;
            @JSONField(name = "dataInfo")
            private List<DataInfoDTO> dataInfo;

            @NoArgsConstructor
            @Data
            public static class DataInfoDTO {
                @JSONField(name = "financialType")
                private String financialType;
                @JSONField(name = "year")
                private String year;
                @JSONField(name = "indicatorData")
                private BigDecimal indicatorData;
                @JSONField(name = "otherIndicator")
                private BigDecimal otherIndicator;
                @JSONField(name = "f10Type")
                private String f10Type;
                @JSONField(name = "endDate")
                private String endDate;
            }
        }

        @NoArgsConstructor
        @Data
        public static class NetMargin {
            @JSONField(name = "levelThreeType")
            private Integer levelThreeType;
            @JSONField(name = "dataInfo")
            private List<DataInfoDTO> dataInfo;

            @NoArgsConstructor
            @Data
            public static class DataInfoDTO {
                @JSONField(name = "financialType")
                private String financialType;
                @JSONField(name = "year")
                private String year;
                @JSONField(name = "indicatorData")
                private BigDecimal indicatorData;
                @JSONField(name = "otherIndicator")
                private BigDecimal otherIndicator;
                @JSONField(name = "f10Type")
                private String f10Type;
                @JSONField(name = "endDate")
                private String endDate;
            }
        }

        @NoArgsConstructor
        @Data
        public static class Roe {
            @JSONField(name = "levelThreeType")
            private Integer levelThreeType;
            @JSONField(name = "dataInfo")
            private List<DataInfoDTO> dataInfo;

            @NoArgsConstructor
            @Data
            public static class DataInfoDTO {
                @JSONField(name = "financialType")
                private String financialType;
                @JSONField(name = "year")
                private String year;
                @JSONField(name = "indicatorData")
                private BigDecimal indicatorData;
                @JSONField(name = "otherIndicator")
                private BigDecimal otherIndicator;
                @JSONField(name = "f10Type")
                private String f10Type;
                @JSONField(name = "endDate")
                private String endDate;
            }
        }

        @NoArgsConstructor
        @Data
        public static class Roa {
            @JSONField(name = "levelThreeType")
            private Integer levelThreeType;
            @JSONField(name = "dataInfo")
            private List<DataInfoDTO> dataInfo;

            @NoArgsConstructor
            @Data
            public static class DataInfoDTO {
                @JSONField(name = "financialType")
                private String financialType;
                @JSONField(name = "year")
                private String year;
                @JSONField(name = "indicatorData")
                private BigDecimal indicatorData;
                @JSONField(name = "otherIndicator")
                private BigDecimal otherIndicator;
                @JSONField(name = "f10Type")
                private String f10Type;
                @JSONField(name = "endDate")
                private String endDate;
            }
        }

        @NoArgsConstructor
        @Data
        public static class Eps {
            @JSONField(name = "currencyUnit")
            private String currencyUnit;
            @JSONField(name = "levelThreeType")
            private Integer levelThreeType;
            @JSONField(name = "dataInfo")
            private List<DataInfoDTO> dataInfo;

            @NoArgsConstructor
            @Data
            public static class DataInfoDTO {
                @JSONField(name = "financialType")
                private String financialType;
                @JSONField(name = "year")
                private String year;
                @JSONField(name = "indicatorData")
                private BigDecimal indicatorData;
                @JSONField(name = "otherIndicator")
                private BigDecimal otherIndicator;
                @JSONField(name = "f10Type")
                private String f10Type;
                @JSONField(name = "endDate")
                private String endDate;
            }
        }

        @NoArgsConstructor
        @Data
        public static class Fcf {
            @JSONField(name = "currencyUnit")
            private String currencyUnit;
            @JSONField(name = "levelThreeType")
            private Integer levelThreeType;
            @JSONField(name = "dataInfo")
            private List<DataInfoDTO> dataInfo;

            @NoArgsConstructor
            @Data
            public static class DataInfoDTO {
                @JSONField(name = "financialType")
                private String financialType;
                @JSONField(name = "year")
                private String year;
                @JSONField(name = "indicatorData")
                private BigDecimal indicatorData;
                @JSONField(name = "otherIndicator")
                private BigDecimal otherIndicator;
                @JSONField(name = "f10Type")
                private String f10Type;
                @JSONField(name = "endDate")
                private String endDate;
            }
        }

        @NoArgsConstructor
        @Data
        public static class Bvps {
            @JSONField(name = "currencyUnit")
            private String currencyUnit;
            @JSONField(name = "levelThreeType")
            private Integer levelThreeType;
            @JSONField(name = "dataInfo")
            private List<DataInfoDTO> dataInfo;

            @NoArgsConstructor
            @Data
            public static class DataInfoDTO {
                @JSONField(name = "financialType")
                private String financialType;
                @JSONField(name = "year")
                private String year;
                @JSONField(name = "indicatorData")
                private BigDecimal indicatorData;
                @JSONField(name = "otherIndicator")
                private BigDecimal otherIndicator;
                @JSONField(name = "f10Type")
                private String f10Type;
                @JSONField(name = "endDate")
                private String endDate;
            }
        }
    }
}
