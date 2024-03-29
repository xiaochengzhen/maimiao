package com.example.craw.dto.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.craw.http.UnitDeserializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description 主营构成汇总的接口返回DTO
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
                @JSONField(name = "num", deserializeUsing = UnitDeserializer.class)
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
