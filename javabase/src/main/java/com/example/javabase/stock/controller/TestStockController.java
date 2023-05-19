package com.example.javabase.stock.controller;
import com.example.javabase.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description
 * @author xiaobo
 * @date 2023/5/19 14:08
 */
@RestController
public class TestStockController {
    @Autowired
    private StockService stockService;


    @GetMapping("/getStock")
    public String getStockMsg() {
        stockService.batchGetDate();
        return "success";
    }


}
