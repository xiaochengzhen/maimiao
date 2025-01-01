package com.example.reptile.controller;

import com.example.reptile.webmagic.playwrightdown.service.NewsInfoCrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaobo
 * @description
 * @date 2024/12/30 13:33
 */
@RestController
public class NewsInfoCrawController {

    @Autowired
    private NewsInfoCrawService newsInfoCrawService;

    @GetMapping("/stock")
    public void stock() {
        newsInfoCrawService.crawStock();
    }

    @GetMapping("/headlines")
    public void headlines() {
        newsInfoCrawService.crawHeadlines();
    }

    @GetMapping("/symbol")
    public void symbol() {
        newsInfoCrawService.crawSymbol();
    }
}
