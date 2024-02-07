package com.example.craw.http;

import com.alibaba.fastjson.JSON;
import com.example.craw.dto.RequestDTO;
import com.example.craw.dto.response.ChiDTO;
import com.example.craw.mapper.SymbolGradeMapper;
import com.example.craw.model.SymbolGradeDO;
import com.example.craw.util.RestTemplateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 财务指标的handler
 * @author xiaobo
 * @date 2023/11/29 8:59
 */
@Service
public class ChiHandler extends CrawHandler{

    private static final String URL = "https://www.hsi.com.hk/data/schi/rt/index-series/sizeindexes/constituents.do";

    @Autowired
    private RestTemplateUtil restTemplateUtil;
    @Autowired
    private SymbolGradeMapper symbolGradeMapper;

    //匹配相应的handler
    @Override
    public boolean match(CrawEnum crawEnum, String market) {
        return crawEnum.getCode().equals("CHI");
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
            ChiDTO chiDTO = JSON.parseObject(httpResult, ChiDTO.class);
            if (chiDTO != null) {
                List<ChiDTO.IndexSeriesListDTO> indexSeriesList = chiDTO.getIndexSeriesList();
                if (!CollectionUtils.isEmpty(indexSeriesList)) {
                    for (ChiDTO.IndexSeriesListDTO indexSeriesListDTO : indexSeriesList) {
                        List<ChiDTO.IndexSeriesListDTO.IndexListDTO> indexList = indexSeriesListDTO.getIndexList();
                        if (!CollectionUtils.isEmpty(indexList)) {
                            for (ChiDTO.IndexSeriesListDTO.IndexListDTO indexListDTO : indexList) {
                                String indexName = indexListDTO.getIndexName();
                                if (StringUtils.isNotBlank(indexName) && indexName.equals("恒生综合大型股指数")) {
                                    List<ChiDTO.IndexSeriesListDTO.IndexListDTO.ConstituentContentDTO> constituentContent = indexListDTO.getConstituentContent();
                                    if (!CollectionUtils.isEmpty(constituentContent)) {
                                        for (ChiDTO.IndexSeriesListDTO.IndexListDTO.ConstituentContentDTO constituentContentDTO : constituentContent) {
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
        requestDTO.setConvertResult(resultList);
    }

    //转换好的数据存库
    @Override
    void saveData(RequestDTO requestDTO) {
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

}
