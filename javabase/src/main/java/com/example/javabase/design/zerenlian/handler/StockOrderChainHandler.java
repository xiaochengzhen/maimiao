package com.example.javabase.design.zerenlian.handler;

/**
 * @author xiaobo
 * @description 订单提交-库存计算处理器
 * @date 2023/2/20 15:44
 */

import com.example.javabase.design.zerenlian.model.OrderDTO;
import com.example.javabase.design.zerenlian.model.ResultDTO;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(100)
public class StockOrderChainHandler extends AbstractOrderChainHandler {
    /**
     * 执行过滤方法
     *
     * @param orderDTO
     * @return
     */
    @Override
    protected ResultDTO doFilter(OrderDTO orderDTO) {
        System.out.println("库存计算处理");
        return doNextHandler(orderDTO, ResultDTO.ok());
    }
}

