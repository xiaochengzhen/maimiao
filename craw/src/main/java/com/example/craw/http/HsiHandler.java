package com.example.craw.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.craw.dto.RequestDTO;
import com.example.craw.dto.response.CompanyFinancialIndicatorDTO;
import com.example.craw.dto.response.HsiDTO;
import com.example.craw.mapper.CompanyFinancialIndicatorMapper;
import com.example.craw.mapper.CompanyFinancialRealMapper;
import com.example.craw.mapper.SymbolGradeMapper;
import com.example.craw.model.CompanyFinancialIndicatorDO;
import com.example.craw.model.CompanyFinancialRealDO;
import com.example.craw.model.SymbolGradeDO;
import com.example.craw.util.EncodeUtil;
import com.example.craw.util.RestTemplateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.craw.http.CrawConstant.*;
import static com.example.craw.http.MainCompositionHandler.stockIdThreadLocal;

/**
 * @description 财务指标的handler
 * @author xiaobo
 * @date 2023/11/29 8:59
 */
@Service
public class HsiHandler extends CrawHandler{

    private static final String URL = "https://www.hsi.com.hk/data/schi/rt/index-series/hsi/constituents.do";

    @Autowired
    private RestTemplateUtil restTemplateUtil;
    @Autowired
    private SymbolGradeMapper symbolGradeMapper;

    //匹配相应的handler
    @Override
    public boolean match(CrawEnum crawEnum, String market) {
        return crawEnum.getCode().equals("HSI");
    }

    //http 请求数据
    @Override
    void httpRequest(RequestDTO requestDTO) {
        String body = restTemplateUtil.httpGetSimple(URL);
        requestDTO.setHttpResult(body);
    }

    //相应数据转换
    @Override
    void convertResponse(RequestDTO requestDTO) {
        List<SymbolGradeDO> resultList = new ArrayList<>();
        String type = requestDTO.getType();
        String httpResult = requestDTO.getHttpResult();
        if (StringUtils.isNotBlank(httpResult)) {
            HsiDTO hsiDTO = JSON.parseObject(httpResult, HsiDTO.class);
            if (hsiDTO != null) {
                List<HsiDTO.IndexSeriesListDTO> indexSeriesList = hsiDTO.getIndexSeriesList();
                if (!CollectionUtils.isEmpty(indexSeriesList)) {
                    for (HsiDTO.IndexSeriesListDTO indexSeriesListDTO : indexSeriesList) {
                        List<HsiDTO.IndexSeriesListDTO.IndexListDTO> indexList = indexSeriesListDTO.getIndexList();
                        if (!CollectionUtils.isEmpty(indexList)) {
                            for (HsiDTO.IndexSeriesListDTO.IndexListDTO indexListDTO : indexList) {
                                List<HsiDTO.IndexSeriesListDTO.IndexListDTO.SubIndexListDTO> subIndexList = indexListDTO.getSubIndexList();
                                if (!CollectionUtils.isEmpty(subIndexList)) {
                                    for (HsiDTO.IndexSeriesListDTO.IndexListDTO.SubIndexListDTO subIndexListDTO : subIndexList) {
                                        List<HsiDTO.IndexSeriesListDTO.IndexListDTO.SubIndexListDTO.ConstituentContentDTO> constituentContentDTOList = subIndexListDTO.getConstituentContent();
                                        if (!CollectionUtils.isEmpty(constituentContentDTOList)) {
                                            for (HsiDTO.IndexSeriesListDTO.IndexListDTO.SubIndexListDTO.ConstituentContentDTO constituentContentDTO : constituentContentDTOList) {
                                                String code = constituentContentDTO.getCode();
                                                if (StringUtils.isNotBlank(code)) {
                                                    SymbolGradeDO symbolGradeDO = new SymbolGradeDO();
                                                    symbolGradeDO.setSymbol(StringUtils.leftPad(code, 5, "0")+".hk");
                                                    symbolGradeDO.setMarket("hk");
                                                    symbolGradeDO.setType(Integer.valueOf(type));
                                                    resultList.add(symbolGradeDO);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        requestDTO.setConvertResult(resultList);
    }

    @Transactional
    public void ttt() {

    }

    //转换好的数据存库
    @Override
    void saveData(RequestDTO requestDTO) {
        testtra();
        String type = requestDTO.getType();
        Object convertResult = requestDTO.getConvertResult();
        if (convertResult != null) {
            List<SymbolGradeDO> list = (List<SymbolGradeDO>) convertResult;
            if (!CollectionUtils.isEmpty(list)) {
                symbolGradeMapper.deleteByType(Integer.valueOf(type));
                symbolGradeMapper.insertBatch(list);
            }
        }
    }

    @Transactional
    @Override
    public void testtra() {
        System.out.println("");
    }

    public static void main(String[] args) {
        String ss = "00001";
        String s = StringUtils.substringBefore(ss, ".");
        System.out.println(s);

    }
}
