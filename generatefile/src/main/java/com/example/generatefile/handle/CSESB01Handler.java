package com.example.generatefile.handle;

import com.example.generatefile.model.*;
import com.google.common.net.MediaType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @description 
 * @author xiaobo
 * @date 2024/2/2 15:51
 */
public class CSESB01Handler extends GenerateFilesHandler {

    /**
     * @description 请求信息转为模块对象
     * @date 2023/12/14 9:21
     */
    @Override
    protected void convert(GenerateDTO generateDTO) {
        GenerateFilesRequest generateFilesRequest = generateDTO.getGenerateFilesRequest();
        CSESB01Request csesb01Request = (CSESB01Request) generateFilesRequest;
        List<CSESB01Request.GenerateSymbol> generateSymbols = csesb01Request.getGenerateSymbols();
        if (!CollectionUtils.isEmpty(generateSymbols)) {
            CSESB01DTO csesb01DTO = getAtidto(csesb01Request);
            generateDTO.setTemplate(csesb01DTO);
        }
    }

    /**
     * @description 获取行信息列表
     * @date 2023/12/14 9:21
     */
    @Override
    protected void getLinesList(GenerateDTO generateDTO) {
        Object template = generateDTO.getTemplate();
        if (template != null) {
            CSESB01DTO atidto = (CSESB01DTO) template;
            //模板数据转为行信息
            List<String> lineList = new ArrayList<>();
            CSESB01DTO.ControlRecordStart controlRecordStart = atidto.getControlRecordStart();
            lineList.add(getLine(controlRecordStart));
            List<CSESB01DTO.DetailRecord> detailRecordList = atidto.getDetailRecords();
            for (CSESB01DTO.DetailRecord detailRecord : detailRecordList) {
                lineList.add(getLine(detailRecord));
            }
            CSESB01DTO.ControlRecordEnd controlRecordEnd = atidto.getControlRecordEnd();
            lineList.add(getLine(controlRecordEnd));
            generateDTO.setLineList(lineList);
        }
    }

    /**
     * @description 获取生成文件信息
     * @date 2023/12/14 9:21
     */
    @Override
    protected void getFileInfoDTO(GenerateDTO generateDTO) {
        List<String> lineList = generateDTO.getLineList();
        if (!CollectionUtils.isEmpty(lineList)) {
            FileInfoDTO fileInfoDTO = new FileInfoDTO();
            fileInfoDTO.setLinesList(lineList);
            fileInfoDTO.setFileName("CSESB01");
            fileInfoDTO.setSuffix(".txt");
            fileInfoDTO.setMediaTyp(MediaType.OPENDOCUMENT_TEXT);
            generateDTO.setFileInfoDTO(fileInfoDTO);
        }
    }

    /**
     * @description 获取模板对象
     * @date 2023/12/14 9:21
     */
    private CSESB01DTO getAtidto(CSESB01Request csesb01Request) {
        List<CSESB01Request.GenerateSymbol> generateSymbols = csesb01Request.getGenerateSymbols();
        CSESB01DTO atidto = new CSESB01DTO();
        //start
        CSESB01DTO.ControlRecordStart controlRecordStart = new CSESB01DTO.ControlRecordStart();
        controlRecordStart.setCcassDate(csesb01Request.getCcassDate());
        //detail
        ArrayList<CSESB01DTO.DetailRecord> detailRecords = new ArrayList<>();
        for (CSESB01Request.GenerateSymbol generateSymbol : generateSymbols) {
            CSESB01DTO.DetailRecord detailRecord = new CSESB01DTO.DetailRecord();
            detailRecord.setStockCode(generateSymbol.getStockCode());
            detailRecord.setStockAccountCode(generateSymbol.getStockAccountCode());
            detailRecord.setStockAccountBalance(generateSymbol.getStockAccountBalance());
            detailRecord.setSignOfStockBalance(generateSymbol.getSignOfStockBalance());
            detailRecord.setRecordChecksum(Integer.parseInt(detailRecord.getStockCode())+Integer.parseInt(detailRecord.getStockAccountBalance())+"");
            detailRecords.add(detailRecord);
        }
        //end
        int sumOfAllStockBalances = detailRecords.stream().mapToInt(s -> Integer.parseInt(s.getStockAccountBalance())).sum();
        int sumOfAllStockValues = detailRecords.stream().mapToInt(s -> Integer.parseInt(StringUtils.replace(s.getStockAccountValue(), ".", ""))).sum();
        int recordCheckSumSum = detailRecords.stream().mapToInt(s -> Integer.parseInt(s.getRecordChecksum())).sum();
        CSESB01DTO.ControlRecordEnd controlRecordEnd = new CSESB01DTO.ControlRecordEnd();
        controlRecordEnd.setSumOfAllStockBalances(sumOfAllStockBalances+"");
        controlRecordEnd.setSumOfAllStockValues(sumOfAllStockValues+"");
        controlRecordEnd.setSumOfAllRecordChecksums(recordCheckSumSum+"");
        atidto.setControlRecordStart(controlRecordStart);
        atidto.setDetailRecords(detailRecords);
        atidto.setControlRecordEnd(controlRecordEnd);
        return atidto;
    }
}
