package com.example.craw.dto.vo;

import lombok.Data;

/**
 * @description 查询主营构成信息的请求类
 * @author xiaobo
 * @date 2023/12/4 10:08
 */
@Data
public class ListMainCompositionVO {

    /**
     * 名称
     */
    private String name;

    /**
     * 营收
     */
    private String price;

    /**
     * 比例
     */
    private String ratio;
}
