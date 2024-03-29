package com.example.craw.dto.query;

import lombok.Data;

/**
 * @description 查询主营构成信息的请求类
 * @author xiaobo
 * @date 2023/12/4 10:08
 */
@Data
public class ListMainCompositionQuery {
    /**
     * 标的
     */
    private String symbol;

    /**
     * 时间
     */
    private Long date;

    /**
     * 主营类型：4=地区； 8=业务
     */
    private Integer type;
}
