package com.example.craw.test;

import com.example.craw.test.ATIDTO.ControlRecordEnd;
import com.example.craw.test.ATIDTO.ControlRecordStart;
import com.example.craw.test.ATIDTO.DetailRecord;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @description 
 * @author xiaobo
 * @date 2023/12/12 13:06
 */
@Service
public class CcassService {

    private static final String FILE_PATH = "CCLTN05.txt";

    public void analysisFiles() {
        List<String> lineList = readConfig(FILE_PATH);
        if (!CollectionUtils.isEmpty(lineList)) {
            CCLTN05DTO ccltn05DTO = new CCLTN05DTO();
            CCLTN05DTO.ControlRecordStart controlRecordStart = new CCLTN05DTO.ControlRecordStart();
            List<CCLTN05DTO.DetailRecord> detailRecords = new ArrayList<>();
            ccltn05DTO.setControlRecordStart(controlRecordStart);
            ccltn05DTO.setDetailRecords(detailRecords);
            for (int i = 0; i < lineList.size(); i++) {
                String line = lineList.get(i);
                if (i == 0) {
                    setValue(line, controlRecordStart);
                } else {
                    String substring = line.substring(0, 1);
                    if ("1".equals(substring)) {
                        CCLTN05DTO.DetailRecord detailRecord = new CCLTN05DTO.DetailRecord();
                        setValue(line, detailRecord);
                        detailRecords.add(detailRecord);
                    }
                }
            }
            if (controlRecordStart != null && "CCLTN05".equals(controlRecordStart.getReportID())) {

            }
        }
    }

    private void setValue(String line, Object object) {
        StringBuilder stringBuilder = new StringBuilder(line);
        Class<?> aClass = object.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            int lineLength = stringBuilder.length();
            declaredField.setAccessible(true);
            boolean annotationPresent = declaredField.isAnnotationPresent(RuleAnnotation.class);
            if (annotationPresent) {
                RuleAnnotation annotation = declaredField.getAnnotation(RuleAnnotation.class);
                String rule = annotation.value();
                Pair<String, Integer> pair = getRawString(rule, stringBuilder.toString());
                String rawStr = pair.getLeft();
                try {
                    declaredField.set(object, rawStr);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                Integer length = pair.getRight();
                String subString = stringBuilder.substring(length, lineLength);
                stringBuilder = new StringBuilder(subString);
            }
        }
    }

    private static List<String> readConfig(String filePath) {
        List<String> lineList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lineList.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineList;
    }

    public static Pair<String, Integer> getRawString(String rule, String data) {
        String result = "";
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
        int count = length - number;
        if (count < 0) {
            throw new RuntimeException("数据错误");
        }
        String subData = "";
        String subInteger = "";
        String subDecimal = "";
        switch (ruleHeader) {
            case "X":
                subData = StringUtils.substring(data, 0, number);
                result = StringUtils.trim(subData);
                break;
            case "9":
                subData = StringUtils.substring(data, 0, number);
                result = subData;
                break;
            case "V9":
                subInteger = StringUtils.substring(data, 0, integer);
                subDecimal = StringUtils.substring(data, subInteger.length(), number);
                if (Integer.parseInt(subDecimal) == 0) {
                    result = StringUtils.stripStart(subInteger, "0");
                } else {
                    result = StringUtils.stripStart(subInteger, "0") + "." + subDecimal;
                }
                break;
            default:
                break;
        }
        return Pair.of(result, number);
    }
}
