package com.example.reptile.playwright;

/**
 * @author xiaobo
 * @description
 * @date 2024/12/28 9:42
 */
import com.microsoft.playwright.*;
import org.springframework.util.CollectionUtils;

import java.nio.file.Paths;
import java.util.List;

public class PlaywrightExample1 {
    public static void main(String[] args) {
        // 初始化 Playwright
        try (Playwright playwright = Playwright.create()) {
            // 启动 Chromium 浏览器
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setDevtools(true));
            BrowserContext context = browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("videos")));
            Page page = context.newPage();
            context.setDefaultTimeout(120000);
            // 导航到网页
            Response navigate = page.navigate("https://cn.investing.com/search/?q=00700");
            int status = navigate.status();
            System.out.println(status);
           /* page.evaluate("document.querySelector('input[type=hidden]').style.display = 'block';");
            page.evaluate("document.querySelector('input[type=hidden]').style.visibility = 'visible';");
            String content1 = page.content();
            Locator a = page.locator("a");
            a.click();
            String content2 = page.content();
            List<Locator> all = page.locator("//input").all();
            for (Locator locator : all) {
                locator.click();
            }
*/
            String content = page.content();
            List<Locator> group = page.locator("//div[@class='searchSectionMain']/h2[@class='groupHeader']").all();
            if (!CollectionUtils.isEmpty(group)) {
                for (Locator locator : group) {
                    String s = locator.innerHTML();
                    if (s.equals("资讯")) {
                        List<Locator> articleItem = locator.locator("../div[@class='largeTitle']/div[@class='articleItem']").all();
                        if (!CollectionUtils.isEmpty(articleItem)) {
                            for (Locator article : articleItem) {
                                String headerPicture = article.locator("xpath=.//a/img").getAttribute("src");
                                String headerContent = article.locator("xpath=.//div/a").innerHTML();
                                String linkAddress = article.locator("xpath=.//div/a").getAttribute("href");
                                String publisherTime = article.locator("xpath=.//div/div/time").innerHTML();
                                String publisherName = article.locator("xpath=.//div/div/span").innerHTML();
                            }
                        }
                    }
                }
            }
          //  System.out.println(content);
            // 截图
         //   page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot111.png")));
            // 关闭浏览器
            browser.close();
            context.close();
        }
    }
}
