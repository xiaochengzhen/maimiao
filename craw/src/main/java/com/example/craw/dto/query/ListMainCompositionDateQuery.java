package com.example.craw.dto.query;

import lombok.Data;

/**
 * @description 查询主营构成时间的请求类
 * @author xiaobo
 * @date 2023/12/4 10:08
 */
@Data
public class ListMainCompositionDateQuery {
    /**
     * 标的
     */
    private String symbol;

    /**
     * 主营类型：4=地区； 8=业务
     */
    private String type;
}
