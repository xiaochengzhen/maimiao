package com.example.craw.dto.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description 
 * @author xiaobo
 * @date 2023/12/5 10:07
 */
@Data
public class CompanyExecutivesVO {

    /**
     * 名称
     */
    private String name;

    /**
     * 变动后持股比例
     */
    private BigDecimal shareRatio;
}
