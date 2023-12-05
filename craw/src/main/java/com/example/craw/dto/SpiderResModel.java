package com.example.craw.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description 
 * @author xiaobo
 * @date 2023/12/5 14:51
 */
@Data
public class SpiderResModel {

    private List<String> titleList;
    private List<String> dateList;
    private List<List<DataModel>> data;

    @Data
    public static class DataModel {
        private String ratio;
        private BigDecimal value;
    }
}
