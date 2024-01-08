package com.example.craw.dto.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.craw.http.UnitDeserializer;
import com.example.craw.http.ZhNameDeserializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description 主营构成详细信息us接口返回DTO
 * @author xiaobo
 * @date 2023/12/2 10:20
 */
@NoArgsConstructor
@Data
public class CompositionDataDTO {

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

        @Data
        @NoArgsConstructor
        public static class PriceItemDTO {
            @JSONField(name = "name", deserializeUsing = ZhNameDeserializer.class)
            private String name;
            private String price;
            @JSONField(name = "ratio")
            private String ratio;

            @JSONField(name = "price")
            public String getPrice() {
                return price;
            }

            @JSONField(alternateNames = {"mainOperIncome","price"}, deserializeUsing = UnitDeserializer.class)
            public void setPrice(String price) {
                this.price = price;
            }
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
