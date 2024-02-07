package com.example.generatefile.model;
import com.google.common.net.MediaType;
import lombok.Data;

import java.util.List;

/**
 * @description 
 * @author xiaobo
 * @date 2024/2/2 16:04
 */
@Data
public class FileInfoDTO {

    private List<String> linesList;
    private String fileName;
    private String suffix;
    private MediaType mediaTyp;
}
