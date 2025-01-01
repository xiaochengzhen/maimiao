package com.example.reptile.webmagic.playwrightdown.service;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Response;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.selector.PlainText;

/**
 * @author xiaobo
 * @description
 * @date 2024/12/30 10:30
 */
public class PlayWrightDownloader extends HttpClientDownloader {

    private Browser browser;

    public PlayWrightDownloader(Browser browser) {
        this.browser = browser;
    }

    /**
     * 下载页面
     */
    @Override
    public Page download(Request request, Task task) {
        String url = request.getUrl();
        BrowserContext context = browser.newContext(new Browser.NewContextOptions());
        com.microsoft.playwright.Page page = context.newPage();
        context.setDefaultTimeout(120000);
        // 导航到网页
        Response navigate = page.navigate(url);
        // 渲染后的 HTML
        String content = page.content();
        us.codecraft.webmagic.Page resultPage = new us.codecraft.webmagic.Page();
        resultPage.setRawText(content);
        resultPage.setUrl(new PlainText(url));
        resultPage.setDownloadSuccess(true);
        resultPage.setStatusCode(navigate.status());
        resultPage.setRequest(request);
        onSuccess(resultPage, task);
        context.close();
        page.close();
        return resultPage;
    }
}
