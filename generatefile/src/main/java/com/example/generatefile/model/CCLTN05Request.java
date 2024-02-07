
package com.example.generatefile.model;

import com.example.generatefile.annotation.RuleAnnotation;
import lombok.Data;

import java.util.List;

/**
 * @description 
 * @author xiaobo
 * @date 2024/2/2 14:34
 */
@Data
public class CCLTN05Request extends GenerateFilesRequest{

    private String tradeDate;
    private String settlementDate;
    private List<GenerateSymbol> generateSymbols;

    @Data
    public static class GenerateSymbol{
        private String stockCode;
        private String sell;
        private String tradeTime;
        private String tradeQuantity;
        private String tradePrice;

    }

}
