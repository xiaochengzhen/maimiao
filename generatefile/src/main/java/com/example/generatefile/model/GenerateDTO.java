package com.example.generatefile.model;

import lombok.Data;

import java.util.List;

/**
 * @description 
 * @author xiaobo
 * @date 2024/2/2 15:57
 */
@Data
public class GenerateDTO {
    /**
     * 请求类
     */
    private GenerateFilesRequest generateFilesRequest;
    /**
     * 模板信息
     */
    private Object template;
    /**
     * 文件行信息列表
     */
    private List<String> lineList;
    /**
     * 上传文件信息
     */
    private FileInfoDTO fileInfoDTO;
    /**
     * 上传文件地址列表
     */
    private List<String> urlList;
}
