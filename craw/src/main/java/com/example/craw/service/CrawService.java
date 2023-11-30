package com.example.craw.service;

import com.example.craw.dto.RequestDTO;
import com.example.craw.http.CrawEnum;
import com.example.craw.http.CrawHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String symbol = "00002.hk";
        CrawEnum[] values = CrawEnum.values();
        for (CrawEnum value : values) {
            for (CrawHandler crawHandler : crawHandlerList) {
                boolean match = crawHandler.match(value);
                if (match) {
                    RequestDTO requestDTO = new RequestDTO();
                    requestDTO.setSymbol(symbol);
                    requestDTO.setType(value.getType());
                    requestDTO.setLanguage(value.getLanguage());
                    crawHandler.craw(requestDTO);
                    break;
                }
            }
        }
    }
}
