package com.example.craw.service;

import com.example.craw.dto.RequestDTO;
import com.example.craw.http.CrawEnum;
import com.example.craw.http.CrawHandler;
import com.example.craw.mapper.SymbolMapper;
import com.example.craw.model.SymbolDO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.ibatis.javassist.compiler.ast.Symbol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class CrawService {

    @Autowired
    private List<CrawHandler> crawHandlerList;
    @Autowired
    private SymbolMapper symbolMapper;

    //爬取数据
    public void craw(Pair<List<CrawEnum>, String> params) {
        if (params != null) {
            String symbol = params.getRight();
            List<CrawEnum> values = params.getLeft();
            if (StringUtils.isNotBlank(symbol)) {
                handle(symbol, values);
            }
        }
    }

    private void handle(String symbol,List<CrawEnum> values) {
        String marketCode = MARKET_CODE_HK;
        String marketType = MARKET_TYPE_HK;
        String market = StringUtils.substringAfter(symbol, ".");
        if (StringUtils.substringAfter(symbol, ".").equalsIgnoreCase("us")) {
            marketCode = MARKET_CODE_US;
            marketType = MARKET_TYPE_US;
        }
        for (CrawEnum value : values) {
            if (!value.getSkip()) {
                for (CrawHandler crawHandler : crawHandlerList) {
                    boolean match = crawHandler.match(value, market.toUpperCase(Locale.ROOT));
                    if (match) {
                        RequestDTO requestDTO = new RequestDTO();
                        requestDTO.setSymbol(symbol);
                        requestDTO.setType(value.getType());
                        requestDTO.setLanguage(value.getLanguage());
                        requestDTO.setLevelThreeType(value.getLevelThreeType());
                        requestDTO.setMarketCode(marketCode);
                        requestDTO.setMarketType(marketType);
                        crawHandler.craw(requestDTO);
                        break;
                    }
                }
            }
        }
        stockIdThreadLocal.remove();
    }

}
