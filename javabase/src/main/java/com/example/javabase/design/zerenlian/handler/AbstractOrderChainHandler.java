package com.example.javabase.design.zerenlian.handler;

import com.example.javabase.design.zerenlian.model.OrderDTO;
import com.example.javabase.design.zerenlian.model.ResultDTO;

/**
 * @author xiaobo
 * @description 处理器抽象类
 * @date 2023/2/20 15:42
 */
public abstract class AbstractOrderChainHandler {

    private AbstractOrderChainHandler nextHandler;

    /**
     * 执行过滤方法
     *
     * @param orderDTO
     * @return
     */
    abstract protected ResultDTO doFilter(OrderDTO orderDTO);

    /**
     * 执行下一个处理器
     *
     * @param orderDTO
     * @param resultDTO
     * @return
     */
    protected ResultDTO doNextHandler(OrderDTO orderDTO, ResultDTO resultDTO) {
        if (nextHandler == null) {
            return resultDTO;
        }
        return nextHandler.doFilter(orderDTO);
    }

    public void setNextHandler(AbstractOrderChainHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
