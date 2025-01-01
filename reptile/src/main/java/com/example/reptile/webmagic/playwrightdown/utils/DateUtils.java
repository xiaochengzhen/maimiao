package com.example.reptile.webmagic.playwrightdown.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * @author xiaobo
 * @description
 * @date 2024/12/31 8:58
 */
@Slf4j
public class DateUtils {

    public static Date getDateByString(String dateString, String language) {
        Date date;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
        if ("en_US".equals(language)) {
            formatter = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
        }
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            log.warn("date 转换错误");
            date = new Date();
        }
        return date;
    }

    public static void main(String[] args) {
        Date dateByString = getDateByString("2024年12月27日", "zh_CN");
        System.out.println(dateByString);
        Date dateByString1 = getDateByString("Dec 29, 2024", "en_US");
        System.out.println(dateByString1);
    }

    public static String getLanguageByUrl(String url) {
        String language = "zh_CN";
        if (url.contains("https://www.investing.com")) {
            language = "en_US";
        }
        return language;
    }

}
