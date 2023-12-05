package com.example.craw.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.craw.dto.query.ListMainCompositionDateQuery;
import com.example.craw.dto.query.ListMainCompositionQuery;
import com.example.craw.dto.vo.ListMainCompositionVO;
import com.example.craw.mapper.CompanyMainCompositionMapper;
import com.example.craw.model.CompanyMainCompositionDO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.craw.http.CrawConstant.REGION_TYPE;

/**
 * @description 主营构成的service
 * @author xiaobo
 * @date 2023/12/4 10:05
 */
@Service
public class CompanyMainCompositionService {

    @Autowired
    private CompanyMainCompositionMapper companyMainCompositionMapper;

    /**
     * @description 获取主营构成时间列表
     * @author xiaobo
     * @date 2023/12/4 10:32
     */
    public List<String> listMainCompositionDate(ListMainCompositionDateQuery listMainCompositionDateQuery) {
        String symbol = listMainCompositionDateQuery.getSymbol();
        String type = listMainCompositionDateQuery.getType();
        List<String> list = companyMainCompositionMapper.listQuarter(symbol, type);
        if (!CollectionUtils.isEmpty(list)) {
            Comparator<String> comparator = null;
            if (StringUtils.substringAfter(symbol, ".").equalsIgnoreCase("hk")) {
                comparator = Comparator.comparing(s-> StringUtils.substringAfter(s, ")"), Comparator.reverseOrder());
            } else {
                comparator = Comparator.comparing(s-> s, Comparator.reverseOrder());
            }
            list = list.stream().sorted(comparator).collect(Collectors.toList());
        }
        return list;
    }

    /**
     * @description 获取主营构成时间列表
     * @author xiaobo
     * @date 2023/12/4 10:32
     */
    public List<ListMainCompositionVO> listMainComposition(ListMainCompositionQuery listMainCompositionQuery, String language) {
        List<ListMainCompositionVO> resutList = new ArrayList<>();
        String symbol = listMainCompositionQuery.getSymbol();
        String quarter = listMainCompositionQuery.getQuarter();
        String type = listMainCompositionQuery.getType();
        List<CompanyMainCompositionDO> list = companyMainCompositionMapper.list(symbol, quarter, type);
        if (!CollectionUtils.isEmpty(list)) {
            //如果不传入日期，去最新的
            if (StringUtils.isBlank(quarter)) {
                Comparator<CompanyMainCompositionDO> comparator = null;
                if (StringUtils.substringAfter(symbol, ".").equalsIgnoreCase("hk")) {
                    comparator = Comparator.comparing(s-> StringUtils.substringAfter(s.getQuarter(), ")"), Comparator.reverseOrder());
                } else {
                    comparator = Comparator.comparing(s-> s.getQuarter(), Comparator.reverseOrder());
                }
                list = list.stream().sorted(comparator).collect(Collectors.toList());
            }
            CompanyMainCompositionDO companyMainCompositionDO = list.get(0);
            if (companyMainCompositionDO != null) {
                String data = "";
                //地区
                if (type.equals(REGION_TYPE)) {
                    data = companyMainCompositionDO.getRegion();
                } else {
                    //业务
                    data = companyMainCompositionDO.getBusiness();
                }
                if (StringUtils.isNotBlank(data)) {
                    resutList = JSONArray.parseArray(data, ListMainCompositionVO.class);
                    for (ListMainCompositionVO listMainCompositionVO : resutList) {
                        String name = listMainCompositionVO.getName();
                        JSONObject jsonObject = JSON.parseObject(name);
                        String string = jsonObject.getString(language);
                        listMainCompositionVO.setName(string);
                    }
                }
            }
        }
        return resutList;
    }
}
