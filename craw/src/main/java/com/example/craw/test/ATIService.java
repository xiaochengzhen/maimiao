package com.example.craw.test;
import com.example.craw.test.ATIDTO.ControlRecordEnd;
import com.example.craw.test.ATIDTO.ControlRecordStart;
import com.example.craw.test.ATIDTO.DetailRecord;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @description 
 * @author xiaobo
 * @date 2023/12/12 13:06
 */
@Service
public class ATIService {

    private static final String FILE_PATH = "test.txt";

    public void generateFiles(ATIRequest atiRequest) {
        ATIDTO ati = getATI(atiRequest);
        List<String> lineList = getLineList(ati);
        writeToFile(lineList);
    }

    public ATIDTO getATI(ATIRequest atiRequest) {
        List<ATIRequest.SymbolDO> symbols = atiRequest.getSymbols();
        ATIDTO atidto = new ATIDTO();
        ControlRecordStart controlRecordStart = new ControlRecordStart();
        controlRecordStart.setRecordType("0");
        controlRecordStart.setFileIndicator("0001");
        controlRecordStart.setParticipantID("123456");
        controlRecordStart.setSenderBIC("");
        controlRecordStart.setParticipantOwnFileReference("");
        controlRecordStart.setFileTransmissionDate("20231212");
        controlRecordStart.setFileName("ATI BATCH INPUT");
        controlRecordStart.setFiller("");

        ArrayList<DetailRecord> detailRecords = new ArrayList<>();
        for (ATIRequest.SymbolDO symbolDO : symbols) {
            String symbol = symbolDO.getSymbol();
            Long securityQuantity = symbolDO.getSecurityQuantity();
            String baseSymbol = StringUtils.substringBefore(symbol, ".");
            DetailRecord detailRecord = new DetailRecord();
            detailRecord.setRecordType("1");
            detailRecord.setStockCode(baseSymbol);
            detailRecord.setIsin("");
            detailRecord.setFromACNumber("1");
            detailRecord.setToACNumber("2");
            detailRecord.setTransferQuantity(securityQuantity+"");
            detailRecord.setRemarks("");
            detailRecord.setRecordChecksum((Integer.parseInt(baseSymbol)+securityQuantity)+"");
            detailRecords.add(detailRecord);
        }

        int stockCodesSum = detailRecords.stream().mapToInt(s -> Integer.parseInt(s.getStockCode())).sum();
        int transferQuantitySum = detailRecords.stream().mapToInt(s -> Integer.parseInt(s.getTransferQuantity())).sum();
        int recordCheckSumSum = detailRecords.stream().mapToInt(s -> Integer.parseInt(s.getRecordChecksum())).sum();
        ControlRecordEnd controlRecordEnd = new ControlRecordEnd();
        controlRecordEnd.setRecordType("2");
        controlRecordEnd.setTotalNumberOfDetailRecords(detailRecords.size()+"");
        controlRecordEnd.setSumOfAllStockCodes(stockCodesSum+"");
        controlRecordEnd.setSumOfAllTransferQuantities(transferQuantitySum+"");
        controlRecordEnd.setSumOfAllRecordChecksums(recordCheckSumSum+"");
        controlRecordEnd.setFiller("");
        atidto.setControlRecordStart(controlRecordStart);
        atidto.setDetailRecords(detailRecords);
        atidto.setControlRecordEnd(controlRecordEnd);
        return atidto;
    }

    public List<String> getLineList(ATIDTO atidto) {
        List<String> list = new ArrayList<>();
        if (atidto != null) {
            ControlRecordStart controlRecordStart = atidto.getControlRecordStart();
            list.add(getLine(controlRecordStart));
            List<DetailRecord> detailRecord = atidto.getDetailRecords();
            for (DetailRecord record : detailRecord) {
                list.add(getLine(record));
            }
            ControlRecordEnd controlRecordEnd = atidto.getControlRecordEnd();
            list.add(getLine(controlRecordEnd));
        }
        return list;
    }

    private String getLine(Object object) {
        String line = "";
        Class<?> aClass = object.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            boolean annotationPresent = declaredField.isAnnotationPresent(RuleAnnotation.class);
            if (annotationPresent) {
                RuleAnnotation annotation = declaredField.getAnnotation(RuleAnnotation.class);
                String rule = annotation.value();
                try {
                    String str = (String) declaredField.get(object);
                    String ruleString = getRuleString(rule, str);
                    line += ruleString;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return line;
    }

    private static void writeToFile(List<String> lineList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String line: lineList) {
                writer.write(line);
                writer.newLine(); // 换行
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getRuleString(String rule, String data) {
        String result = "";
        String ruleHeader = StringUtils.substringBefore(rule, "(");
        int number = Integer.parseInt(StringUtils.substringBetween(rule, "(", ")"));
        int length = data.length();
        int count = number-length;
        if (count < 0) {
            throw new RuntimeException("数据错误");
        }
        switch (ruleHeader) {
            case "X":
                result = " ".repeat(count)+data;
                break;
            case "9":
                result = "0".repeat(count)+data;
                break;
            default:
                break;
        }
        return result;
    }

}
