package com.example.craw.dto.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description 
 * @author xiaobo
 * @date 2023/11/30 18:47
 */
@NoArgsConstructor
@Data
public class CompanyFinancialRealDTO {

    @JSONField(name = "code")
    private Integer code;
    @JSONField(name = "message")
    private String message;
    @JSONField(name = "data")
    private DataDTO data;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JSONField(name = "title")
        private String title;
        @JSONField(name = "dataModule")
        private DataModuleDTO dataModule;
        @JSONField(name = "liveItem")
        private Object liveItem;

        @NoArgsConstructor
        @Data
        public static class DataModuleDTO {
            @JSONField(name = "unit")
            private String unit;
            @JSONField(name = "indicators")
            private List<IndicatorsDTO> indicators;

            @NoArgsConstructor
            @Data
            public static class IndicatorsDTO {
                @JSONField(name = "num")
                private String num;
                @JSONField(name = "radio")
                private String radio;
                @JSONField(name = "subTitle")
                private String subTitle;
                @JSONField(name = "title")
                private String title;
                @JSONField(name = "direct")
                private String direct;
            }
        }
    }
}
