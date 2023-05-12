package com.example.mybatisplusdemo.model;

import com.panpan.maimiaoautoconfigure.annotation.QuoteFields;
import lombok.Data;

/**
 * @author xiaobo
 * @description
 * @date 2023/2/21 11:19
 */
@Data
public class ResultDTO<T> {
    private String code;
    private String msg;
    @QuoteFields
    private T data;


    public ResultDTO(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
