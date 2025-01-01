package com.example.reptile.webmagic.playwrightdown.config;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class PlaywrightConfig {

    @Bean(destroyMethod = "close")
    public Playwright playwright() {
        return Playwright.create();
    }

    @Bean(destroyMethod = "close")
    public Browser browser(Playwright playwright) {
        // 启动 Chromium 浏览器
        return playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
    }

    @Bean(destroyMethod = "close")
    public Playwright playwright1() {
        return Playwright.create();
    }

    @Bean(destroyMethod = "close")
    public Browser browser1(Playwright playwright1) {
        // 启动 Chromium 浏览器
        return playwright1.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
    }
}
