package com.example.craw.http;

import com.example.craw.dto.RequestDTO;
import com.example.craw.util.RestTemplateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @description 获取stockId的handler
 * @author xiaobo
 * @date 2023/11/29 8:59
 */
@Service
public class MainCompositionHandler extends CrawHandler{

    public static final String URL = "https://www.futunn.com/en/stock/{code}/financial/main-composition";
    public static ThreadLocal<String> stockIdThreadLocal = new ThreadLocal<>();

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Override
    public boolean match(CrawEnum crawEnum, String market) {
        return crawEnum.getCode().equals("MAIN_COMPOSITION");
    }

    @Override
    void httpRequest(RequestDTO requestDTO) {
        String symbol = requestDTO.getSymbol();
        Map<String, String> map = new LinkedHashMap<>();
        String symbolMarket = StringUtils.substringBefore(symbol, ".")+"-"+StringUtils.substringAfter(symbol, ".").toUpperCase(Locale.ROOT);
        map.put("code",symbolMarket);
        String body = restTemplateUtil.httpGet(map, null, URL);
        if (StringUtils.isNotBlank(body)) {
            String s = extractJsonContent(body);
            if (StringUtils.isNotBlank(s)) {
                String stockId = StringUtils.substringAfter(s, ":");
                if (StringUtils.isNotBlank(stockId)) {
                    stockIdThreadLocal.set(stockId.replace("\"",""));
                }
            }
        }

    }

    @Override
    void convertResponse(RequestDTO requestDTO) {

    }

    @Override
    void saveData(RequestDTO requestDTO) {

    }

    private static String extractJsonContent(String input) {
        // 使用正则表达式提取 JSON 数据
        String regex = "\"stockId\":\"(\\d+)\"";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group();
        } else {
            throw new IllegalArgumentException("No JSON content found in the input string.");
        }
    }


}
