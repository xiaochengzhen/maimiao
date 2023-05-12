package com.example.javabase.design.zerenlian.handler;

import com.example.javabase.design.zerenlian.model.OrderDTO;
import com.example.javabase.design.zerenlian.model.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author xiaobo
 * @description 订单处理责任链
 * @date 2023/2/20 15:46
 */
@Service
public class OrderChainHandler {

    @Autowired
    private List<AbstractOrderChainHandler> chain;

    private AbstractOrderChainHandler firstHandler;


    @PostConstruct
    private void constructChain() {
        if (chain == null || chain.size() == 0) {
            throw new RuntimeException("not found order chain handler");
        }
        firstHandler = chain.get(0);
        for (int i = 0; i < chain.size(); i++) {
            if (i == chain.size() - 1) {
                chain.get(i).setNextHandler(null);
            } else {
                chain.get(i).setNextHandler(chain.get(i + 1));
            }
        }
    }

    public ResultDTO executionChain(OrderDTO orderDTO) {
        return firstHandler.doFilter(orderDTO);
    }
}
