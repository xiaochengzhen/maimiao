package com.example.craw.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.craw.dto.query.ListMainCompositionQuery;
import com.example.craw.dto.vo.ListMainCompositionDateVO;
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
    public List<ListMainCompositionDateVO> listMainCompositionDate(ListMainCompositionQuery listMainCompositionQuery, String language) {
        List<ListMainCompositionDateVO> resultList = new ArrayList<>();
        List<CompanyMainCompositionDO> list = companyMainCompositionMapper.list(listMainCompositionQuery);
        if (!CollectionUtils.isEmpty(list)) {
            for (CompanyMainCompositionDO companyMainCompositionDO : list) {
                ListMainCompositionDateVO listMainCompositionDateVO = new ListMainCompositionDateVO();
                String dateShow = companyMainCompositionDO.getDateShow();
                Long date = companyMainCompositionDO.getDate();
                if (StringUtils.isNotBlank(dateShow)) {
                    JSONObject jsonObject = JSONObject.parseObject(dateShow);
                    String name = jsonObject.getString(language);
                    listMainCompositionDateVO.setDate(date);
                    listMainCompositionDateVO.setDateShow(name);
                    resultList.add(listMainCompositionDateVO);
                }
            }
        }
        return resultList;
    }

    /**
     * @description 获取主营构成列表
     * @author xiaobo
     * @date 2023/12/4 10:32
     */
    public List<ListMainCompositionVO> listMainComposition(ListMainCompositionQuery listMainCompositionQuery, String language) {
        List<ListMainCompositionVO> resutList = new ArrayList<>();
        Long date = listMainCompositionQuery.getDate();
        List<CompanyMainCompositionDO> list = companyMainCompositionMapper.list(listMainCompositionQuery);
        if (!CollectionUtils.isEmpty(list)) {
            //如果不传入日期，去最新的
            if (date == null) {
                CompanyMainCompositionDO companyMainCompositionDO = list.get(0);
                if (companyMainCompositionDO != null) {
                    String data = companyMainCompositionDO.getBusiness();
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
        }
        return resutList;
    }
}
