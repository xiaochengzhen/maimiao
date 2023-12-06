package com.example.craw.dto.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description 利润表接口返回的DTO
 * @author xiaobo
 * @date 2023/12/1 14:35
 */
@NoArgsConstructor
@Data
public class CompanyHkIncomeStatementDTO {

    @JSONField(name = "code")
    private Integer code;
    @JSONField(name = "message")
    private String message;
    @JSONField(name = "data")
    private DataDTO data;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JSONField(name = "hasMore")
        private Boolean hasMore;
        @JSONField(name = "list")
        private ListDTO list;
        @JSONField(name = "securityMsg")
        private SecurityMsgDTO securityMsg;

        @NoArgsConstructor
        @Data
        public static class ListDTO {
            @JSONField(name = "keys")
            private List<String> keys;
            @JSONField(name = "values")
            private List<List<ValuesDTO>> values;
            @JSONField(name = "title")
            private List<String> title;
            @JSONField(name = "industry")
            private Integer industry;
            @JSONField(name = "willChange")
            private Integer willChange;
            @JSONField(name = "changeTime")
            private String changeTime;

            @NoArgsConstructor
            @Data
            public static class ValuesDTO {
                @JSONField(name = "value")
                private String value;
                @JSONField(name = "raw_value")
                private String rawValue;
                @JSONField(name = "ratio")
                private Object ratio;
                @JSONField(name = "ringRatio")
                private Object ringRatio;
            }
        }

        @NoArgsConstructor
        @Data
        public static class SecurityMsgDTO {
            @JSONField(name = "securityId")
            private Long securityId;
            @JSONField(name = "marketCode")
            private Integer marketCode;
            @JSONField(name = "securityNames")
            private SecurityNamesDTO securityNames;

            @NoArgsConstructor
            @Data
            public static class SecurityNamesDTO {
                @JSONField(name = "en_name")
                private String enName;
                @JSONField(name = "tc_name")
                private String tcName;
                @JSONField(name = "sc_name")
                private String scName;
            }
        }
    }
}
