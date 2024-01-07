package com.example.craw.test;

import lombok.Data;

import java.util.List;

/**
 * @description 
 * @author xiaobo
 * @date 2023/12/12 13:26
 */
@Data
public class ATIRequest {


    private List<SymbolDO> symbols;

    @Data
    public static class SymbolDO{
        private String symbol;
        private Long securityQuantity;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
    }


}
