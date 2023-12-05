package com.example.craw.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.craw.dto.RequestDTO;
import com.example.craw.dto.response.CompositionDataHkDTO;
import com.example.craw.dto.response.CompositionDataUsDTO;
import com.example.craw.mapper.CompanyMainCompositionMapper;
import com.example.craw.model.CompanyMainCompositionDO;
import com.example.craw.util.EncodeUtil;
import com.example.craw.util.RestTemplateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.example.craw.http.CrawConstant.REGION_TYPE;


/**
 * @description 主营构成详细数据的handler（us）
 * @author xiaobo
 * @date 2023/11/29 8:59
 */
@Service
public class CompanyMainCompositionUsHandler extends CrawHandler{

    public static final String URL = "https://www.futunn.com/quote-api/quote-v2/get-composition-data?code={code}&market={market}&marketType={marketType}&type={type}&date={date}";

    @Autowired
    private RestTemplateUtil restTemplateUtil;
    @Autowired
    private CompanyMainCompositionMapper companyMainCompositionMapper;

    //匹配相应的handler
    @Override
    public boolean match(CrawEnum crawEnum, String market) {
        return crawEnum.getCode().equals("COMPANY_MAIN_US") && market.equalsIgnoreCase("US");
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
        String quoteToken = EncodeUtil.getQuoteToken(map);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("quote-token", quoteToken);
        if (StringUtils.isBlank(language) || language.equals("en_US")) {
            String symbolMarket = StringUtils.substringBefore(symbol, ".")+"-"+StringUtils.substringAfter(symbol, ".").toUpperCase(Locale.ROOT);
            httpHeaders.add("referer", "https://www.futunn.com/en/stock/"+symbolMarket+"/financial/main-composition");
        }
        String body = restTemplateUtil.httpGet(map, httpHeaders, URL);
        requestDTO.setHttpResult(body);
    }

    //相应数据转换
    @Override
    void convertResponse(RequestDTO requestDTO) {
        String httpResult = requestDTO.getHttpResult();
        String symbol = requestDTO.getSymbol();
        String type = requestDTO.getType();
        String language = requestDTO.getLanguage();
        if (StringUtils.isNotBlank(httpResult)) {
            JSONObject jsonObject = JSON.parseObject(httpResult);
            Integer code = jsonObject.getInteger("code");
            JSONObject dataJT = jsonObject.getJSONObject("data");
            if (code == 0 && dataJT != null) {
                CompositionDataUsDTO compositionDataUsDTO = JSONObject.parseObject(httpResult, CompositionDataUsDTO.class);
                CompositionDataUsDTO.DataDTO data = compositionDataUsDTO.getData();
                if (data != null) {
                    String date = data.getDate();
                    if (language.equals("en_US")) {
                        //中英文时间格式不同，统一转换为中文格式
                        date = dateFormatConvert(date);
                    }
                    CompanyMainCompositionDO companyMainCompositionDO = new CompanyMainCompositionDO();
                    companyMainCompositionDO.setSymbol(symbol);
                    companyMainCompositionDO.setQuarter(date);
                    List<CompositionDataUsDTO.DataDTO.PriceItemDTO> priceItem = data.getPriceItem();
                    if (!CollectionUtils.isEmpty(priceItem)) {
                        String priceItemStr = JSON.toJSONString(priceItem);
                        if (type.equals(REGION_TYPE)) {
                            companyMainCompositionDO.setRegion(priceItemStr);
                        } else {
                            companyMainCompositionDO.setBusiness(priceItemStr);
                        }
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
                CompanyMainCompositionDO companyMainCompositionDORaw = companyMainCompositionMapper.selectByQuarter(symbol, companyMainCompositionDO.getQuarter());
                if (companyMainCompositionDORaw != null) {
                    companyMainCompositionDO.setId(companyMainCompositionDORaw.getId());
                    if (language.equals("zh_CN")) {
                        if (zhExt(type, companyMainCompositionDO, companyMainCompositionDORaw)) {
                            return;
                        }
                    } else {
                        if (enExt(type, companyMainCompositionDO, companyMainCompositionDORaw)) {
                            return;
                        }
                    }
                    companyMainCompositionMapper.update(companyMainCompositionDO);
                } else {
                    if (type.equals(REGION_TYPE)) {
                        companyMainCompositionDO.setRegion(companyMainCompositionDO.getRegion());
                    } else {
                        companyMainCompositionDO.setBusiness(companyMainCompositionDO.getBusiness());
                    }
                    companyMainCompositionMapper.insert(companyMainCompositionDO);
                }
        }
    }

    //中文存数据
    private boolean zhExt(String type, CompanyMainCompositionDO companyMainCompositionDO, CompanyMainCompositionDO companyMainCompositionDORaw) {
        if (type.equals(REGION_TYPE)) {
            String regionRaw = companyMainCompositionDORaw.getRegion();
            //如果有数据，说明已经存在，不需要去重新修改
            if (StringUtils.isNotBlank(regionRaw)) {
                return true;
            }
        } else {
            String businessRaw = companyMainCompositionDORaw.getBusiness();
            //如果有数据，说明已经存在，不需要去重新修改
            if (StringUtils.isNotBlank(businessRaw)) {
                return true;
            }
        }
        return false;
    }

    //英文存name英文
    private boolean enExt(String type, CompanyMainCompositionDO companyMainCompositionDO, CompanyMainCompositionDO companyMainCompositionDORaw) {
        Map<Integer, String> nameMap = new HashMap<>();
        String str = "";
        if (type.equals(REGION_TYPE)) {
            str = companyMainCompositionDO.getRegion();
        } else {
            str = companyMainCompositionDO.getBusiness();
        }
        //保存name和下标的对应关系，按照中英文数据顺序一致赋值
        List<CompositionDataUsDTO.DataDTO.PriceItemDTO > priceItem = JSONArray.parseArray(str, CompositionDataUsDTO.DataDTO.PriceItemDTO .class);
        if (!CollectionUtils.isEmpty(priceItem)) {
            for (int i = 0; i < priceItem.size(); i++) {
                CompositionDataUsDTO.DataDTO.PriceItemDTO priceItemDTO = priceItem.get(i);
                if (priceItemDTO != null) {
                    String name = priceItemDTO.getName();
                    if (StringUtils.isNotBlank(name)) {
                        name = JSON.parseObject(name).getString("zh_CN");
                    }
                    nameMap.put(i, name);
                }
            }
        }
        if (type.equals(REGION_TYPE)) {
            String regionRaw = companyMainCompositionDORaw.getRegion();
            //如果不存在，说明中文没有执行或者中文没数据，英文不执行
            if (StringUtils.isBlank(regionRaw)) {
                return true;
            }
            List<CompositionDataUsDTO.DataDTO.PriceItemDTO > priceItemRaw = JSONArray.parseArray(regionRaw, CompositionDataUsDTO.DataDTO.PriceItemDTO .class);
            for (int i = 0; i < priceItemRaw.size(); i++) {
                CompositionDataUsDTO.DataDTO.PriceItemDTO priceItemDTORaw = priceItemRaw.get(i);
                String name = priceItemDTORaw.getName();
                JSONObject nameJson = JSON.parseObject(name);
                nameJson.put("en_US", nameMap.get(i));
                priceItemDTORaw.setName(nameJson.toJSONString());
            }
            companyMainCompositionDO.setRegion(JSONObject.toJSONString(priceItemRaw));
        } else {
            String businessRaw = companyMainCompositionDORaw.getBusiness();
            //如果不存在，说明中文没有执行或者中文没数据，英文不执行
            if (StringUtils.isBlank(businessRaw)) {
                return true;
            }
            //修改name结构，增加en_US
            List<CompositionDataUsDTO.DataDTO.PriceItemDTO > priceItemRaw = JSONArray.parseArray(businessRaw, CompositionDataUsDTO.DataDTO.PriceItemDTO .class);
            for (int i = 0; i < priceItemRaw.size(); i++) {
                CompositionDataUsDTO.DataDTO.PriceItemDTO priceItemDTORaw = priceItemRaw.get(i);
                String name = priceItemDTORaw.getName();
                JSONObject nameJson = JSON.parseObject(name);
                nameJson.put("en_US", nameMap.get(i));
                priceItemDTORaw.setName(nameJson.toJSONString());
            }
            companyMainCompositionDO.setBusiness(JSONObject.toJSONString(priceItemRaw));
        }
        return false;
    }

    //转换时间格式
    public static String dateFormatConvert(String date) {
        // 定义输入日期字符串的格式
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        // 定义输出日期字符串的格式
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            // 解析输入日期字符串
            Date inputDate = inputDateFormat.parse(date);
            // 格式化输出日期字符串
            String outputDateStr = outputDateFormat.format(inputDate);
            return outputDateStr;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

}
