package com.example.reptile.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xiaobo
 * @description
 * @date 2024/12/28 9:36
 */
@Slf4j
public class PlaywrightHandler {
    // target page url
    private String targetUrl;
    // observer
    private String targetSelector; //  "#clazz" ".some-div"
    // callback url
    private String callback;

    public static PlaywrightHandler of(String url, String targetSelector, String callback) {
        PlaywrightHandler pwb = new PlaywrightHandler();
        pwb.targetUrl = url;
        pwb.targetSelector = targetSelector;
        pwb.callback = callback;
        return pwb;
    }

    private boolean isClose = true;

    public void run() {
        while (true) {  // 只要playwright启动失败，就一直循环重启
            if (isClose) {
                this.start();
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                log.error("Thread.sleep异常", e);
            }
        }
    }

    private void start() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate(targetUrl);
            // JavaScript: MutationObserver
            String mutationObserver = "const targetNode = document.querySelector('" + targetSelector + "');" +
                    "const config = { attributes: true, childList: true, subtree: true, characterData:true };" +
                    "const observer = new MutationObserver(mutationsList => {" +
                    "    for (const mutation of mutationsList) {" +
                    "       if (mutation.type === 'attributes') {" +
                    "           const form = new FormData();" +
                    "           form.append('url', '" + targetUrl + "');" +
                    "           form.append('name', mutation.attributeName);" +
                    "           form.append('html', mutation.target.outerHTML);" +
                    "           fetch('" + callback + "', {method: 'POST', body: form});" +
                    "       }" +
                    "    }" +
                    "});" +
                    "observer.observe(targetNode, config);";
            log.info(mutationObserver);

            page.evaluate(mutationObserver);

            isClose = false;
            log.info("{} playwright启动成功", targetUrl);
            // 持续监听 (使用循环，但请注意资源消耗，并根据需要添加退出机制)
            while (true) {
                try {
                    Thread.sleep(5000); // 每 5 秒检查一次
                } catch (InterruptedException e) {
                    log.error("Thread.sleep异常", e);
                }
            }
        } catch (Exception e) {
            log.error("playwright异常", e);
            isClose = true;
        }
    }

    @Bean
    public PlaywrightHandler jinPlaywrightBean() {
        return PlaywrightHandler.of("https://www.jin10.com", "#jin_flash_list"
                , "http://localhost:8080/callback/jin");
    }

    @CrossOrigin("*")
    @PostMapping("/jin")
    public String post(@RequestParam("url") String url,     // target url
                       @RequestParam("name") String name,
                       @RequestParam("html") String html) {
//        log.info("url={}, name={}", url, name);
        if (!"class".equals(name)) {
            return "class is " + name;
        }
        if (StringUtils.isBlank(html)) {
            return "target is null";
        }
        Document doc = Jsoup.parse(html);
//        Elements unlock = doc.getElementsByClass("unlock-flash-btn");
//        if (null != unlock && unlock.size() > 0) {
//            return "VIP";
//        }

        Elements content = doc.getElementsByClass("flash-text");
        if (null == content || content.size() == 0) {
            return "flash-text is null";
        }
        Item item = new Item();
        item.setTarget(url);
        item.setName(name);
        item.setContent(content.get(0).text());
        Elements title = doc.getElementsByClass("right-common-title");
        if (null != title && title.size() > 0) {
            item.setTitle(title.get(0).text());
        }
        Elements important = doc.getElementsByClass("is-important");
        if (null != important && important.size() > 0) {
            item.setImportant(true);
        }

        log.info("item={}", item);
        return name;
    }

    @Data
    public class Item {
        // 目标
        String target;  // target url
        String name;    // html attribute / properties
        // 内容
        String title;
        String content;
        boolean important;
    }
}
