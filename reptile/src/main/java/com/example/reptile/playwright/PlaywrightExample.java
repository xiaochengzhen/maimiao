package com.example.reptile.playwright;

/**
 * @author xiaobo
 * @description
 * @date 2024/12/28 9:42
 */
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Cookie;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;

import static com.microsoft.playwright.options.LoadState.NETWORKIDLE;
import static com.microsoft.playwright.options.WaitForSelectorState.VISIBLE;

public class PlaywrightExample {
    public static void main(String[] args) {
        // 初始化 Playwright
        try (Playwright playwright = Playwright.create()) {

            // 启动 Chromium 浏览器
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true).setDevtools(true));
            //   .setArgs(Arrays.asList("--disable-infobars", "--start-maximized")));
                    ; // 无头模式
            // 创建新页面
            BrowserContext context = browser.newContext(new Browser.NewContextOptions());
               //     .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
               //     .setViewportSize(1920, 1080)
                //    .setExtraHTTPHeaders(Map.of(
                //            "Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
                //            "Accept-Encoding", "gzip, deflate, br",
                //            "Accept-Language", "en-US,en;q=0.9",
                //            "Connection", "keep-alive",
                //            "Upgrade-Insecure-Requests", "1"
                //    )));
            // 获取并设置 Cookies
           /* Cookie cookie = new Cookie("cookieName", "cookieValue");
            cookie.setUrl("https://cn.investing.com/");
            context.addCookies(Arrays.asList(
                    cookie
            ));*/
            Page page = context.newPage();
            context.setDefaultTimeout(999999);
            // 导航到网页
            page.navigate("https://cn.investing.com/search/?q=%E6%B8%AF%E8%82%A1%E5%B8%82%E5%9C%BA&tab=news");
            page.waitForLoadState();
         /*   page.click("//*[@id=\"__next\"]/header/div[1]/section/div[3]/ul/li[1]/button/span");
            page.click("//*[@id=\":R1b6:\"]/form/button[4]/span");
            Locator username = page.locator("//*[@id=\":R1b6:\"]/form/div[3]/input");
            username.fill("bo.xiao@ebonex.cc");
            Locator password = page.locator("//*[@id=\":R1b6:\"]/form/div[5]/input");
            password.fill("xiaobo150320");
            page.click("//*[@id=\":R1b6:\"]/form/button");*/

            // 通过 CSS Selector 定位搜索框
          /*  Locator searchBox = page.locator("//*[@id=\"__next\"]/header/div[1]/section/div[2]/div[1]/div/form/input");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 在搜索框中输入文本
            searchBox.fill("港股市场");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 提交搜索框（模拟按下回车）
            searchBox.press("Enter");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            page.click("//*[@id=\"searchPageResultsTabs\"]/li[3]/a");*/

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // page.waitForLoadState(NETWORKIDLE);  // 等待网络请求完成
             //   page.click("//*[@id=\"searchPageResultsTabs\"]/li[3]/a");
            //page.waitForSelector("div.some-dynamic-content");
            String content = page.content();
            System.out.println(content);
           // page.waitForFunction("document.querySelector('data-tab-name').style.display !== 'none'");
           /* String content1 = page.content();
            System.out.println(content1);
            page.click("//*[@id=\"searchPageResultsTabs\"]/li[3]/a");
            String content2 = page.content();
            System.out.println(content2);
            // 等待动态内容加载
            page.waitForSelector("//*[@id=\"fullColumn\"]/div/div[4]", new Page.WaitForSelectorOptions().setState(VISIBLE));
            // 获取页面 HTML 内容
            String content3 = page.content();
            System.out.println(content3);*/
            // 截图保存
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot3.png")));

            // 关闭浏览器
            browser.close();
        }
    }
}
