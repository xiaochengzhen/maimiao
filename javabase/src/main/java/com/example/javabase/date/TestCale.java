package com.example.javabase.date;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class TestCale {
    public static void main(String[] args) throws ParseException {
        // 将字符串按照指定格式转成Calendar对象（指定时区为东八区：北京时间）
        Calendar c1 = ToolForDateTime.getCalendarByDateString("yyyy-MM-dd HH:mm:ss SSS", "2020-09-14 17:33:33 000", 8);
        int year = ToolForDateTime.getYearByCalendar(c1);
        int month = ToolForDateTime.getMonthByCalendar(c1);
        int day = ToolForDateTime.getDayByCalendar(c1);
        int hour = ToolForDateTime.getHourByCalendar(c1);
        int minute = ToolForDateTime.getMinuteByCalendar(c1);
        int second = ToolForDateTime.getSecondByCalendar(c1);
        int millisecond = ToolForDateTime.getMillisecondByCalendar(c1);
        int week = ToolForDateTime.getWeekByCalendar(c1);

        System.out.println("将字符串按格式转成Date得到的日期时间结果：" + year + "年" + month + "月" + day + " " + hour + ":" + minute + ":" + second + " " + millisecond + " 星期" + week);

        // 将Date转化成目标时区对应的Calendar对象（将日历从东八区：北京时间转到西三区：巴西时间）
        Calendar c2 = ToolForDateTime.getCalendarByConvertTimeZone(c1, 8, -3);
        year = ToolForDateTime.getYearByCalendar(c2);
        month = ToolForDateTime.getMonthByCalendar(c2);
        day = ToolForDateTime.getDayByCalendar(c2);
        hour = ToolForDateTime.getHourByCalendar(c2);
        minute = ToolForDateTime.getMinuteByCalendar(c2);
        second = ToolForDateTime.getSecondByCalendar(c2);
        millisecond = ToolForDateTime.getMillisecondByCalendar(c2);
        week = ToolForDateTime.getWeekByCalendar(c2);
        System.out.println("将Calendar转化成目标时区对应的Calendar对象：" + year + "年" + month + "月" + day + " " + hour + ":" + minute + ":" + second + " " + millisecond + " 星期" + week);

        System.out.println("c1所在月的最后一天是" + ToolForDateTime.getMaxDayForCalendar(c1) + "号");
    }
}

