package com.example.craw.dto.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description 
 * @author xiaobo
 * @date 2024/1/25 9:44
 */
@NoArgsConstructor
@Data
public class ChiDTO {


    @JSONField(name = "requestName")
    private String requestName;
    @JSONField(name = "requestDate")
    private String requestDate;
    @JSONField(name = "dateFormat")
    private String dateFormat;
    @JSONField(name = "duration")
    private String duration;
    @JSONField(name = "indexSeriesList")
    private List<IndexSeriesListDTO> indexSeriesList;
    @JSONField(name = "refreshFrequency")
    private Integer refreshFrequency;

    @NoArgsConstructor
    @Data
    public static class IndexSeriesListDTO {
        @JSONField(name = "seriesName")
        private String seriesName;
        @JSONField(name = "seriesCode")
        private String seriesCode;
        @JSONField(name = "indexList")
        private List<IndexListDTO> indexList;
        @JSONField(name = "constituentsDate")
        private String constituentsDate;
        @JSONField(name = "constituentDesc")
        private String constituentDesc;
        @JSONField(name = "hasContribution")
        private Boolean hasContribution;

        @NoArgsConstructor
        @Data
        public static class IndexListDTO {
            @JSONField(name = "indexName")
            private String indexName;
            @JSONField(name = "isTableOpen")
            private Boolean isTableOpen;
            @JSONField(name = "constituentsCount")
            private Integer constituentsCount;
            @JSONField(name = "constituentTemplate")
            private String constituentTemplate;
            @JSONField(name = "constituentContent")
            private List<ConstituentContentDTO> constituentContent;

            @NoArgsConstructor
            @Data
            public static class ConstituentContentDTO {
                @JSONField(name = "code")
                private String code;
            }
        }
    }
}
