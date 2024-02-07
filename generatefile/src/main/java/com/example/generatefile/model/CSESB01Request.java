
package com.example.generatefile.model;

import lombok.Data;
import java.util.List;

/**
 * @description 
 * @author xiaobo
 * @date 2024/2/2 14:34
 */
@Data
public class CSESB01Request extends GenerateFilesRequest{

    private String ccassDate;
    private List<GenerateSymbol> generateSymbols;

    @Data
    public static class GenerateSymbol{

        private String stockCode;
        private String stockAccountCode;
        private String stockAccountBalance;
        private String signOfStockBalance;

    }

}
