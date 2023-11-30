package com.example.craw.dto;

import lombok.Data;

/**
 * @description 
 * @author xiaobo
 * @date 2023/11/29 16:56
 */
@Data
public class RequestDTO {

    private String type;
    private String language;
    private String symbol;
    private String httpResult;
    private Object convertResult;
}
