package com.example.craw.dto.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description 
 * @author xiaobo
 * @date 2023/12/2 10:20
 */
@NoArgsConstructor
@Data
public class CompositionDataUsDTO {

    @JSONField(name = "code")
    private Integer code;
    @JSONField(name = "message")
    private String message;
    @JSONField(name = "data")
    private DataDTO data;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JSONField(name = "priceItem")
        private List<PriceItemDTO> priceItem;
        @JSONField(name = "screenDates")
        private List<ScreenDatesDTO> screenDates;
        @JSONField(name = "date")
        private String date;
        @JSONField(name = "currency")
        private String currency;

        @NoArgsConstructor
        @Data
        public static class PriceItemDTO {
            @JSONField(name = "name")
            private String name;
            @JSONField(name = "mainOperIncome")
            private String mainOperIncome;
            @JSONField(name = "ratio")
            private String ratio;
        }

        @NoArgsConstructor
        @Data
        public static class ScreenDatesDTO {
            @JSONField(name = "date")
            private Integer date;
            @JSONField(name = "showName")
            private String showName;
        }
    }
}
