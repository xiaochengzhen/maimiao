package com.example.generatefile.handle;

import com.example.generatefile.model.*;
import com.google.common.net.MediaType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
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
public class CCLTN05Handler extends GenerateFilesHandler {

    /**
     * @description 请求信息转为模块对象
     * @date 2023/12/14 9:21
     */
    @Override
    protected void convert(GenerateDTO generateDTO) {
        GenerateFilesRequest generateFilesRequest = generateDTO.getGenerateFilesRequest();
        CCLTN05Request ccltn05Request = (CCLTN05Request) generateFilesRequest;
        List<CCLTN05Request.GenerateSymbol> generateSymbols = ccltn05Request.getGenerateSymbols();
        if (!CollectionUtils.isEmpty(generateSymbols)) {
            CCLTN05DTO ccltn05DTO = getAtidto(ccltn05Request);
            generateDTO.setTemplate(ccltn05DTO);
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
            CCLTN05DTO atidto = (CCLTN05DTO) template;
            //模板数据转为行信息
            List<String> lineList = new ArrayList<>();
            CCLTN05DTO.ControlRecordStart controlRecordStart = atidto.getControlRecordStart();
            lineList.add(getLine(controlRecordStart));
            List<CCLTN05DTO.DetailRecord> detailRecordList = atidto.getDetailRecords();
            for (CCLTN05DTO.DetailRecord detailRecord : detailRecordList) {
                lineList.add(getLine(detailRecord));
            }
            CCLTN05DTO.ControlRecordEnd controlRecordEnd = atidto.getControlRecordEnd();
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
            fileInfoDTO.setFileName("CCLTN05");
            fileInfoDTO.setSuffix(".txt");
            fileInfoDTO.setMediaTyp(MediaType.OPENDOCUMENT_TEXT);
            generateDTO.setFileInfoDTO(fileInfoDTO);
        }
    }

    /**
     * @description 获取模板对象
     * @date 2023/12/14 9:21
     */
    private CCLTN05DTO getAtidto(CCLTN05Request atiRequest) {
        List<CCLTN05Request.GenerateSymbol> generateSymbols = atiRequest.getGenerateSymbols();
        CCLTN05DTO atidto = new CCLTN05DTO();
        //start
        CCLTN05DTO.ControlRecordStart controlRecordStart = new CCLTN05DTO.ControlRecordStart();
        controlRecordStart.setTradeDate(atiRequest.getTradeDate());
        controlRecordStart.setSettlementDate(atiRequest.getSettlementDate());
        //detail
        ArrayList<CCLTN05DTO.DetailRecord> detailRecords = new ArrayList<>();
        for (CCLTN05Request.GenerateSymbol generateSymbol : generateSymbols) {
            CCLTN05DTO.DetailRecord detailRecord = new CCLTN05DTO.DetailRecord();
            detailRecord.setStockCode(generateSymbol.getStockCode());
            detailRecord.setSell(generateSymbol.getSell());
            detailRecord.setTradeTime(generateSymbol.getTradeTime());
            detailRecord.setTradeQuantity(generateSymbol.getTradeQuantity());
            detailRecord.setTradePrice(new BigDecimal(generateSymbol.getTradePrice()).setScale(3, RoundingMode.HALF_UP).toPlainString());
            detailRecord.setTradeValue(new BigDecimal(detailRecord.getTradeQuantity()).multiply(new BigDecimal(detailRecord.getTradePrice())).setScale(2, RoundingMode.HALF_UP).toPlainString());
            detailRecord.setRecordChecksum(Integer.parseInt(detailRecord.getStockCode())+Integer.parseInt(detailRecord.getTradeQuantity())
                    +Integer.parseInt(StringUtils.replace(detailRecord.getTradePrice(), ".", ""))
                    +Integer.parseInt(StringUtils.replace(detailRecord.getTradeValue(), ".", ""))
                    +Integer.parseInt(detailRecord.getAccruedInterest())+"");
            detailRecords.add(detailRecord);
        }
        //end
        int stockCodesSum = detailRecords.stream().mapToInt(s -> Integer.parseInt(s.getStockCode())).sum();
        int transferQuantitySum = detailRecords.stream().mapToInt(s -> Integer.parseInt(s.getTradeQuantity())).sum();
        int transferPriceSum = detailRecords.stream().mapToInt(s -> Integer.parseInt(StringUtils.replace(s.getTradePrice(), ".", ""))).sum();
        int transferValueSum = detailRecords.stream().mapToInt(s -> Integer.parseInt(StringUtils.replace(s.getTradeValue(), ".",""))).sum();
        int accruedInterestSum = detailRecords.stream().mapToInt(s -> Integer.parseInt(StringUtils.replace(s.getAccruedInterest(), ".", ""))).sum();
        int recordCheckSumSum = detailRecords.stream().mapToInt(s -> Integer.parseInt(s.getRecordChecksum())).sum();
        CCLTN05DTO.ControlRecordEnd controlRecordEnd = new CCLTN05DTO.ControlRecordEnd();
        controlRecordEnd.setSumOfAllStockCodes(stockCodesSum+"");
        controlRecordEnd.setSumOfAllTradeQuantities(transferQuantitySum+"");
        controlRecordEnd.setSumOfAllTradePrices(transferPriceSum+"");
        controlRecordEnd.setSumOfAllTradeValues(transferValueSum+"");
        controlRecordEnd.setSumOfAllAccruedInterest(accruedInterestSum+"");
        controlRecordEnd.setSumOfAllRecordChecksums(recordCheckSumSum+"");
        atidto.setControlRecordStart(controlRecordStart);
        atidto.setDetailRecords(detailRecords);
        atidto.setControlRecordEnd(controlRecordEnd);
        return atidto;
    }
}
