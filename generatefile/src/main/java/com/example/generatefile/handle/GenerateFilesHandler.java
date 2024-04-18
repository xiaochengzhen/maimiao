package com.example.generatefile.handle;

import com.example.generatefile.annotation.RuleAnnotation;
import com.example.generatefile.model.FileInfoDTO;
import com.example.generatefile.model.GenerateDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.jackson.JsonObjectDeserializer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

@Slf4j
public abstract class GenerateFilesHandler {

    /**
     * @description 生成文件
     * @date 2023/12/14 9:21
     */
    public List<String> generateFiles(GenerateDTO generateDTO) {        //数据转换为模板对象
        convert(generateDTO);        //模板对象转为行信息列表
        getLinesList(generateDTO);        //获取生成文件信息
        getFileInfoDTO(generateDTO);        //上传文件
        uploadFiles(generateDTO);
        return generateDTO.getUrlList();
    }

    /**
     * @description 数据转换
     * @date 2023/12/14 9:21
     */
    protected abstract void convert(GenerateDTO generateDTO);
    /**
     * @description 获取文本列表
     * @date 2023/12/14 9:21
     */
    protected abstract void getLinesList(GenerateDTO generateDTO);
    /**
     * @description 获取生成文件信息
     * @date 2023/12/14 9:21
     */
    protected abstract void getFileInfoDTO(GenerateDTO generateDTO);
    /**
     * @description 上传文件
     * @date 2023/12/14 9:21
     */
    protected void uploadFiles(GenerateDTO generateDTO) {
        FileInfoDTO fileInfoDTO = generateDTO.getFileInfoDTO();
        if (fileInfoDTO != null) {
            List<String> lineList = fileInfoDTO.getLinesList();
            String fileName = fileInfoDTO.getFileName();
            String suffix = fileInfoDTO.getSuffix();
            File tmpFile = null;
            try {
             //   tmpFile = Files.createTempFile(fileName, suffix).toFile();
                try (FileWriter writer = new FileWriter("D:/"+fileName+suffix)) {
                    for (String line: lineList) {
                        writer.write(line);
                        writer.write(System.lineSeparator()); // 换行
                    }                }
            } catch (IOException e) {
                log.error("写入文件失败{}",e);
            }
        }
    }



    /**
     * @description 根据对象组装行信息
     * @date 2023/12/14 9:21
     */
    protected String getLine(Object object) {
        String line = "";
        Class<?> aClass = object.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            boolean annotationPresent = declaredField.isAnnotationPresent(RuleAnnotation.class);
            if (annotationPresent) {
                RuleAnnotation annotation = declaredField.getAnnotation(RuleAnnotation.class);
                String rule = annotation.value();
                String dirention = annotation.dirention();
                try {
                    String str = (String) declaredField.get(object);
                    String ruleString = getRuleString(rule, dirention, str==null?"":str);
                    line += ruleString;
                } catch (IllegalAccessException e) {
                    log.error("根据对象组装行信息失败", e);
                }
            }
        }
        return line;
    }

    /**
     * @description 对象数据转换为文档数据
     * @date 2023/12/14 9:21
     */
    protected static String getRuleString(String rule, String dirention , String data) {        String result = "";
        int length = data.length();
        String ruleHeader = "";
        Integer number = null;
        Integer integer = null;
        Integer decimal = null;
        boolean contains = StringUtils.contains(rule, ")V9(");
        if (contains) {
            ruleHeader = "V9";
            integer = Integer.valueOf(StringUtils.substringBetween(rule, "9(", ")V9("));
            decimal = Integer.valueOf(StringUtils.substringBetween(rule, ")V9(", ")"));
            number = integer + decimal;
        } else {
            ruleHeader = StringUtils.substringBefore(rule, "(");
            number = Integer.valueOf(StringUtils.substringBetween(rule, "(", ")"));
        }
        int count = number-length;
        if (count < 0) {
            log.error("");
        }
        //补位
        switch (ruleHeader) {
            case "X":
            if ("L".equals(dirention)) {
                result = StringUtils.leftPad(data, number, "");
            } else {
                result = StringUtils.rightPad(data, number, "");
            }
            break;
            case "9":
                result = StringUtils.leftPad(data, number, "0");
                break;
            case "V9":
                String interString = "";
                String decimalString = "";
                if (data.contains(".")) {
                    interString = StringUtils.leftPad(StringUtils.substringBefore(data, "."), integer, "0");
                    decimalString = StringUtils.rightPad(StringUtils.substringAfter(data, "."), decimal, "0");
                } else {
                    interString = StringUtils.leftPad(data, integer, "0");
                    decimalString = StringUtils.rightPad("", decimal, "0");
                }
                result = interString + decimalString;
                break;
            default:
                break;
        }
        return result;
    }



}