package com.example.javabase.date;
/**
 * @description
 * @author xiaobo
 * @date 2023/4/20 07:59
 */

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static java.time.ZoneOffset.UTC;

/**
 * @description
 * @author xiaobo
 * @date 2023/4/20 7:59
 */
public class DateTest {
  /*  public static void main(String[] args) throws ParseException {
        Date date = new Date();
        String language = "EN";
        String date1 = getDate(language, date);
        System.out.println(date1);
      //  test();
    }
*/
    public static String getPattern(Date date, String language) {
        String pattern = "";
        int type = 1;   //1=不同年；2=昨日； 3=当天
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar currentCalendar = Calendar.getInstance();
        if (calendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR)) {
            if (calendar.get(Calendar.DAY_OF_YEAR) == currentCalendar.get(Calendar.DAY_OF_YEAR)){
                type = 3;
            } else if (currentCalendar.get(Calendar.DAY_OF_YEAR) - calendar.get(Calendar.DAY_OF_YEAR) == 1) {
                type = 2;
            }
        }
        switch (type) {
            case 1:
                if ("EN".equals(language)) {
                    pattern = "MMM dd yyyy,hh:mm a";
                } else {
                    pattern = "yyyy年MM月dd日 a hh:mm";
                }
                break;
            case 2:
                if ("EN".equals(language)) {
                    pattern = "'Yesterday',hh:mm a";
                } else {
                    pattern = "昨天 a hh:mm";
                }
                break;
            case 3:
                if ("EN".equals(language)) {
                    pattern = "hh:mm a";
                } else {
                    pattern = "a hh:mm";
                }
                break;
            default:
                break;
        }
        return pattern;
    }

    public static String getDate(String language, Date date) {
        if ("EN".equals(language)) {
            return getDateStringEn(date, getPattern(date, language));
        } else {
            return getDateStringCh(date, getPattern(date, language));
        }
    }

    public static String getDateStringEn(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
        String today = simpleDateFormat.format(date);
        return today;
    }

    public static String getDateStringCh(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.SIMPLIFIED_CHINESE);
        String today = simpleDateFormat.format(date);
        return today;
    }

    public static void test() throws ParseException {
        String ss = "2022-10-01";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(ss);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar currentCalendar = Calendar.getInstance();
        int i = calendar.get(Calendar.DAY_OF_YEAR);
        int i1 = currentCalendar.get(Calendar.DAY_OF_YEAR);

        System.out.println(i);
        System.out.println(i1);

        int m = calendar.get(Calendar.MONTH);
        int m1 = currentCalendar.get(Calendar.MONTH);

        System.out.println(m);
        System.out.println(m1);
    }

    public static void main(String[] args) throws ParseException {
       String dateStr = "2019-12-31 23:00:00";
        // 按照本地时区解析字符串成Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = dateFormat.parse(dateStr);
        // 使用目标时区格式化Date
      /*  dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+9:00"));
        System.out.println(dateFormat.format(date1));
        LocalDateTime now = LocalDateTime.now(Clock.systemUTC());*/
        Calendar calendar= Calendar.getInstance(TimeZone.getTimeZone(UTC));
        calendar.setTime(date1);
        int i = calendar.get(Calendar.DAY_OF_YEAR);
        System.out.println(i);

        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+10"));
        String format = dateFormat.format(date1);
        System.out.println(format);

        Calendar calendar1= Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2= Calendar.getInstance(TimeZone.getTimeZone(UTC));
        calendar2.setTime(date1);
        System.out.println(calendar1.get(Calendar.DAY_OF_YEAR));
        System.out.println(calendar2.get(Calendar.DAY_OF_YEAR));
    }


}
