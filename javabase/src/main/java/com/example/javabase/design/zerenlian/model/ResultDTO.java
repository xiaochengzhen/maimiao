package com.example.javabase.design.zerenlian.model;

/**
 * @author xiaobo
 * @description
 * @date 2023/2/20 15:40
 */
public class ResultDTO {
    private String msg;

    public static ResultDTO ok(){
        return new ResultDTO();
    }

    public ResultDTO() {
        msg = "ok";
    }

    public String getMsg() {
        return msg;
    }

}
