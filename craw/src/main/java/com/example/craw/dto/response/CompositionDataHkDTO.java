package com.example.craw.dto.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.craw.http.ZhNameDeserializer;
import com.example.craw.http.UnitDeserializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description 
 * @author xiaobo
 * @date 2023/11/29 10:06
 */
@NoArgsConstructor
@Data
public class CompositionDataHkDTO {

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
        @JSONField(name = "mainIncome")
        private List<MainIncomeDTO> mainIncome;

        @NoArgsConstructor
        @Data
        public static class MainIncomeDTO {
            @JSONField(name = "period")
            private String period;
            @JSONField(name = "date")
            private Integer date;
            @JSONField(name = "currency")
            private String currency;
            @JSONField(name = "priceItem")
            private List<PriceItemDTO> priceItem;

            @NoArgsConstructor
            @Data
            public static class PriceItemDTO {
                @JSONField(name = "name", deserializeUsing = ZhNameDeserializer.class)
                private String name;
                @JSONField(name = "price", deserializeUsing = UnitDeserializer.class)
                private String price;
                @JSONField(name = "ratio")
                private String ratio;
                @JSONField(name = "yearOnYearRatio")
                private String yearOnYearRatio;
            }
        }
    }
}
