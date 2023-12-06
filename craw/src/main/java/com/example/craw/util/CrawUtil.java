package com.example.craw.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.craw.dto.SpiderResModel;
import com.example.craw.dto.vo.ListSingleIncomeStatementVO;
import com.example.craw.http.IncomeKeyAnnotation;
import com.example.craw.model.CompanyHkIncomeStatementDO;
import com.example.craw.model.CompanyUsIncomeStatementDO;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description 
 * @author xiaobo
 * @date 2023/12/6 10:33
 */
public class CrawUtil {

    //单个数据处理
    public static  ListSingleIncomeStatementVO handleSingle(String data, String quarter) {
        ListSingleIncomeStatementVO listSingleIncomeStatementVO = new ListSingleIncomeStatementVO();
        JSONObject jsonObject = JSON.parseObject(data);
        String ratio = jsonObject.getString("ratio");
        BigDecimal value = jsonObject.getBigDecimal("raw_value");
        listSingleIncomeStatementVO.setValue(value);
        listSingleIncomeStatementVO.setRatio(ratio);
        listSingleIncomeStatementVO.setQuarter(quarter);
        return listSingleIncomeStatementVO;
    }

    //单个数据处理
    public static  SpiderResModel.DataModel getDataModel(String data) {
        SpiderResModel.DataModel dataModel = new SpiderResModel.DataModel();
        JSONObject jsonObject = new JSONObject();
        String ratio = null;
        BigDecimal value = null;
        if (StringUtils.isNotBlank(data)) {
            jsonObject = JSON.parseObject(data);
            ratio = jsonObject.getString("ratio");
            value = jsonObject.getBigDecimal("raw_value");
        }
        dataModel.setValue(value);
        dataModel.setRatio(ratio);
        return dataModel;
    }

    //排序过滤日期
    public static  List<String> listDate(Integer period, List<String> list) {
        List<String> newList = null;
        list = list.stream().sorted(Comparator.comparing(s-> StringUtils.substringAfter(s, ")"), Comparator.reverseOrder())).collect(Collectors.toList());
        switch (period) {
            case 1:
                newList = list.stream().filter(s->StringUtils.containsAny(s,"Q1", "Q2", "Q3", "Q4")).collect(Collectors.toList());
                break;
            case 2:
                newList = list.stream().filter(s->StringUtils.containsAny(s, "Q6", "Q9")).collect(Collectors.toList());
                break;
            case 3:
                newList = list.stream().filter(s->StringUtils.containsAny(s,"FY")).collect(Collectors.toList());
                break;
            default:
                break;
        }
        return newList;
    }

    //获取titlemap
    public static Map<String, String> getTitleMap(String language, String market, Class clazz) {
        Map<String, String> titleMap = new LinkedHashMap<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(IncomeKeyAnnotation.class)) {
                IncomeKeyAnnotation annotation = field.getAnnotation(IncomeKeyAnnotation.class);
                String value = annotation.value();
                String title = "";
                if ("zh_CN".equals(language)) {
                    title = annotation.zhName();
                } else {
                    title = annotation.enName();
                }
                titleMap.put(value, title);
            }
        }
        return titleMap;
    }
}
