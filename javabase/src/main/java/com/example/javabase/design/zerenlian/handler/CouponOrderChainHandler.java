package com.example.javabase.design.zerenlian.handler;

import com.example.javabase.design.zerenlian.model.OrderDTO;
import com.example.javabase.design.zerenlian.model.ResultDTO;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * 订单提交-优惠券计算处理器
 */
@Service
@Order(200)
public class CouponOrderChainHandler extends AbstractOrderChainHandler {
    /**
     * 执行过滤方法
     *
     * @param orderDTO
     * @return
     */
    @Override
    protected ResultDTO doFilter(OrderDTO orderDTO) {
        System.out.println("优惠券处理");
        return doNextHandler(orderDTO, ResultDTO.ok());
    }
}
