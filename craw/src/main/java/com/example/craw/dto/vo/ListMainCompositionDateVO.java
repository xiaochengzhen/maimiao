package com.example.craw.dto.vo;

import lombok.Data;

/**
 * @description 查询主营构成时间的请求类
 * @author xiaobo
 * @date 2023/12/4 10:08
 */
@Data
public class ListMainCompositionDateVO {


    /**
     * 日期
     */
    private String dateShow;

    /**
     * 日期
     */
    private Long date;
}
