package com.example.craw.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.craw.dto.RequestDTO;
import com.example.craw.dto.response.CompositionDataDTO;
import com.example.craw.mapper.CompanyMainCompositionMapper;
import com.example.craw.model.CompanyMainCompositionDO;
import com.example.craw.util.EncodeUtil;
import com.example.craw.util.RestTemplateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.example.craw.http.CrawConstant.MARKET_TYPE_US;
import static com.example.craw.http.CrawConstant.REGION_TYPE;


/**
 * @description 主营构成详细数据的handler（us）
 * @author xiaobo
 * @date 2023/11/29 8:59
 */
@Service
public class CompanyMainCompositionHandler extends CrawHandler{

    public static final String URL = "https://www.futunn.com/quote-api/quote-v2/get-composition-data?code={code}&market={market}&marketType={marketType}&type={type}&date={date}";

    @Autowired
    private RestTemplateUtil restTemplateUtil;
    @Autowired
    private CompanyMainCompositionMapper companyMainCompositionMapper;

    //匹配相应的handler
    @Override
    public boolean match(CrawEnum crawEnum, String market) {
        return crawEnum.getCode().equals("COMPANY_MAIN");
    }

    //http 请求数据
    @Override
    void httpRequest(RequestDTO requestDTO) {
        String date = requestDTO.getDate();
        String symbol = requestDTO.getSymbol();
        String type = requestDTO.getType();
        String language = requestDTO.getLanguage();
        Map<String, String> map = new LinkedHashMap<>();
        map.put("code",StringUtils.substringBefore(symbol, "."));
        map.put("market",StringUtils.substringAfter(symbol, "."));
        map.put("marketType",requestDTO.getMarketType());
        map.put("type",type);
        map.put("date",date);
        HttpHeaders httpHeaders = new HttpHeaders();
        String body = restTemplateUtil.httpGet1(map, httpHeaders, URL, language, symbol);
        requestDTO.setHttpResult(body);
    }

    //相应数据转换
    @Override
    void convertResponse(RequestDTO requestDTO) {
        String httpResult = requestDTO.getHttpResult();
        String symbol = requestDTO.getSymbol();
        String type = requestDTO.getType();
        if (StringUtils.isNotBlank(httpResult)) {
            JSONObject jsonObject = JSON.parseObject(httpResult);
            Integer code = jsonObject.getInteger("code");
            String dataJT = jsonObject.getString("data");
            if (code == 0 && StringUtils.isNotBlank(dataJT) && !("[]").equals(dataJT)) {
                CompositionDataDTO compositionDataDTO = JSONObject.parseObject(httpResult, CompositionDataDTO.class);
                CompositionDataDTO.DataDTO data = compositionDataDTO.getData();
                if (data != null) {
                    String dateShow = data.getDate();
                    Long date = Long.valueOf(requestDTO.getDate());
                    CompanyMainCompositionDO companyMainCompositionDO = new CompanyMainCompositionDO();
                    companyMainCompositionDO.setSymbol(symbol);
                    companyMainCompositionDO.setDate(date);
                    companyMainCompositionDO.setDateShow(dateShow);
                    List<CompositionDataDTO.DataDTO.PriceItemDTO> priceItem = data.getPriceItem();
                    if (!CollectionUtils.isEmpty(priceItem)) {
                        String priceItemStr = JSON.toJSONString(priceItem);
                        companyMainCompositionDO.setBusiness(priceItemStr);
                        companyMainCompositionDO.setType(Integer.valueOf(type));
                        requestDTO.setConvertResult(companyMainCompositionDO);
                    }
                }
            }
        }
    }

    //转换好的数据存库
    @Override
    void saveData(RequestDTO requestDTO) {
        Object convertResult = requestDTO.getConvertResult();
        String symbol = requestDTO.getSymbol();
        String type = requestDTO.getType();
        String language = requestDTO.getLanguage();
        if (convertResult != null) {
            CompanyMainCompositionDO companyMainCompositionDO = (CompanyMainCompositionDO) convertResult;
            CompanyMainCompositionDO companyMainCompositionDORaw = companyMainCompositionMapper.selectByParams(Integer.valueOf(type), symbol, companyMainCompositionDO.getDate());
            if (companyMainCompositionDORaw != null) {
                companyMainCompositionDO.setId(companyMainCompositionDORaw.getId());
                if (language.equals("en_US")) {
                    enExt(companyMainCompositionDO, companyMainCompositionDORaw);
                    companyMainCompositionMapper.update(companyMainCompositionDO);
                }
            } else {
                if (language.equals("zh_CN")) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put(language, companyMainCompositionDO.getDateShow());
                    companyMainCompositionDO.setDateShow(jsonObject.toJSONString());
                    companyMainCompositionMapper.insert(companyMainCompositionDO);
                }
            }
        }
    }

    //英文存name英文
    private void enExt(CompanyMainCompositionDO companyMainCompositionDO, CompanyMainCompositionDO companyMainCompositionDORaw) {
        Map<Integer, String> nameMap = new HashMap<>();
        String str = companyMainCompositionDO.getBusiness();
        //保存name和下标的对应关系，按照中英文数据顺序一致赋值
        List<CompositionDataDTO.DataDTO.PriceItemDTO> priceItem = JSONArray.parseArray(str, CompositionDataDTO.DataDTO.PriceItemDTO .class);
        if (!CollectionUtils.isEmpty(priceItem)) {
            for (int i = 0; i < priceItem.size(); i++) {
                CompositionDataDTO.DataDTO.PriceItemDTO priceItemDTO = priceItem.get(i);
                if (priceItemDTO != null) {
                    String name = priceItemDTO.getName();
                    if (StringUtils.isNotBlank(name)) {
                        name = JSON.parseObject(name).getString("zh_CN");
                    }
                    nameMap.put(i, name);
                }
            }
        }
        String businessRaw = companyMainCompositionDORaw.getBusiness();
        List<CompositionDataDTO.DataDTO.PriceItemDTO > priceItemRaw = JSONArray.parseArray(businessRaw, CompositionDataDTO.DataDTO.PriceItemDTO .class);
        for (int i = 0; i < priceItemRaw.size(); i++) {
            CompositionDataDTO.DataDTO.PriceItemDTO priceItemDTORaw = priceItemRaw.get(i);
            String name = priceItemDTORaw.getName();
            JSONObject nameJson = JSON.parseObject(name);
            nameJson.put("en_US", nameMap.get(i));
            priceItemDTORaw.setName(nameJson.toJSONString());
        }
        companyMainCompositionDO.setBusiness(JSONObject.toJSONString(priceItemRaw));
        String dateShowZh = companyMainCompositionDORaw.getDateShow();
        String dateShowUs = companyMainCompositionDO.getDateShow();
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isNotBlank(dateShowZh)) {
            jsonObject = JSONObject.parseObject(dateShowZh);
        }
        jsonObject.put("en_US", dateShowUs);
        companyMainCompositionDO.setDateShow(jsonObject.toJSONString());
    }


}
