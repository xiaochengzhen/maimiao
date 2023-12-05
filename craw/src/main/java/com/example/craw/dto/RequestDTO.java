package com.example.craw.dto;

import com.example.craw.http.CrawEnum;
import lombok.Data;

/**
 * @description 通用请求类
 * @author xiaobo
 * @date 2023/11/29 16:56
 */
@Data
public class RequestDTO {

    /**
     *  @see CrawEnum
     */
    private String type;
    /**
     * 语言 zh_CN en_US
     */
    private String language;
    /**
     * 市场：1=港股； 11=美股
     */
    private String marketCode;
    /**
     * 市场：1=港股； 2=美股
     */
    private String marketType;
    /**
     *  @see CrawEnum
     */
    private String levelThreeType;
    private String date;
    private String symbol;
    private String httpResult;
    private Object convertResult;
}
