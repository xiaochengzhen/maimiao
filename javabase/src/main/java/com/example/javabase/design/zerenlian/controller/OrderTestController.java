package com.example.javabase.design.zerenlian.controller;

import com.example.javabase.design.zerenlian.handler.OrderChainHandler;
import com.example.javabase.design.zerenlian.model.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xiaobo
 * @description
 * @date 2023/2/20 15:46
 */
@Controller
@RequestMapping("/lsz")
public class OrderTestController {

    @Autowired
    private OrderChainHandler orderChainHandler;


    @ResponseBody
    @RequestMapping("testOrder")
    public Object testOrder(String orderNo){
        return orderChainHandler.executionChain(new OrderDTO()).getMsg();
    }
}
