package com.example.craw.service;

import com.example.craw.dto.RequestDTO;
import com.example.craw.http.CrawEnum;
import com.example.craw.http.CrawHandler;
import com.example.craw.mapper.SymbolMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Locale;

import static com.example.craw.http.CrawConstant.*;
import static com.example.craw.http.MainCompositionHandler.stockIdThreadLocal;

/**
 * @description 爬取财务指标、主营构成、利润表的service层
 * @author xiaobo
 * @date 2023/11/29 9:02
 */
@Service
public class SymbolGradeService {

    @Autowired
    private List<CrawHandler> crawHandlerList;

    //爬取数据
    public void craw(List<CrawEnum> params) {
        handle(params);
    }

    private void handle(List<CrawEnum> params) {
        if (CollectionUtils.isEmpty(params)) {
            params = CrawEnum.getCrawEnum(2);
        }
        for (CrawEnum value : params) {
            if (!value.getSkip()) {
                for (CrawHandler crawHandler : crawHandlerList) {
                    boolean match = crawHandler.match(value, null);
                    if (match) {
                        RequestDTO requestDTO = new RequestDTO();
                        requestDTO.setType(value.getType());
                        crawHandler.craw(requestDTO);
                        break;
                    }
                }
            }
        }
    }

}
