package com.example.craw.service;

import com.example.craw.dto.RequestDTO;
import com.example.craw.http.CrawEnum;
import com.example.craw.http.CrawHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

import static com.example.craw.http.MainCompositionHandler.stockIdThreadLocal;

/**
 * @description 
 * @author xiaobo
 * @date 2023/11/29 9:02
 */
@Service
public class CrawService {

    @Autowired
    private List<CrawHandler> crawHandlerList;

    public void craw() {
        String symbol = "AAPL.us";
       // String symbol = "00700.hk";
        String marketCode = "1";
        String marketType = "1";
        String market = StringUtils.substringAfter(symbol, ".");
        if (StringUtils.substringAfter(symbol, ".").equalsIgnoreCase("us")) {
            marketCode = "11";
            marketType = "2";
        }
        CrawEnum[] values = CrawEnum.values();
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
