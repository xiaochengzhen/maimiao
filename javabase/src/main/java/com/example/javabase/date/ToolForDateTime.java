package com.example.javabase.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 沈杰
 * 2020-09-07 09:36:21
 * 工具类：日期时间相关
 */
public class ToolForDateTime {
    private static final TimeZone timeZone = TimeZone.getTimeZone("GMT");

    /**
     * 获取当前指定时区的Calendar对象
     *
     * @param hour 相当于格林威治时间的小时数（用以表示时区）
     * @return 指定时区的Calendar对象
     */
    public static Calendar getNowCalendar(Integer hour) {
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.add(Calendar.HOUR, hour);
        return calendar;
    }

    /**
     * 根据时间格式、时间字符串来解析时间，得到对应的Calendar
     *
     * @param format     时间字符串的解析格式
     * @param dateString 时间字符串
     * @return 指定时间字符串在指定格式下对应的Calender对象
     * @throws ParseException 当dateString的格式与指定的format格式不一致时，抛出此异常
     */
    public static Calendar getCalendarByDateString(String format, String dateString, Integer timeZoneHour) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = simpleDateFormat.parse(dateString);
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, timeZoneHour);
        return calendar;
    }

    /**
     * 根据Date获取一个Calendar对象（根据timeZoneHour获取对应的Calendar对象）
     */
    public static Calendar getCalendarByConvertTimeZone(Calendar originalCalendar, Integer originalTimeZoneHour, Integer objectiveTimeZoneHour) {
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTime(originalCalendar.getTime());
        calendar.add(Calendar.HOUR, objectiveTimeZoneHour - originalTimeZoneHour);
        return calendar;
    }

    /**
     * 获取指定Calendar的年份
     *
     * @param calendar 已指定了时区的Calendar对象
     * @return 如：2020等
     */
    public static Integer getYearByCalendar(Calendar calendar) {
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取指定时区当前的年份
     *
     * @param hour 相对于格林威治时间偏移的小时数（用以表示时区）
     * @return 如：2020等
     */
    public static Integer getNowYear(Integer hour) {
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.add(Calendar.HOUR, hour);
        return getYearByCalendar(calendar);
    }

    /**
     * 获取指定Calendar的月份
     *
     * @param calendar 已指定了时区的Calendar对象
     * @return 闭区间[1, 12]
     */
    public static Integer getMonthByCalendar(Calendar calendar) {
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取指定时区当前的月份
     *
     * @param hour 相对于格林威治时间偏移的小时数（用以表示时区）
     * @return 闭区间[1, 12]
     */
    public static Integer getNowMonth(Integer hour) {
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.add(Calendar.HOUR, hour);
        return getMonthByCalendar(calendar);
    }

    /**
     * 获取指定Calendar的日期
     *
     * @param calendar 已指定了时区的Calendar对象
     * @return 闭区间[1~31]
     */
    public static Integer getDayByCalendar(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取指定时区当前日期
     *
     * @param hour 相对于格林威治时间偏移的小时数（用以表示时区）
     * @return 闭区间[1~31]
     */
    public static Integer getNowDay(Integer hour) {
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.add(Calendar.HOUR, hour);
        return getDayByCalendar(calendar);
    }

    /**
     * 获取指定Calendar的星期
     *
     * @param calendar 已指定了时区的Calendar对象
     * @return 闭区间[1~7]
     */
    public static Integer getWeekByCalendar(Calendar calendar) {
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return week == 0 ? 7 : week;
    }

    /**
     * 获取指定时区当前星期几
     *
     * @param hour 相对于格林威治时间偏移的小时数（用以表示时区）
     * @return 闭区间[1~7]
     */
    public static Integer getNowWeek(Integer hour) {
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.add(Calendar.HOUR, hour);
        return getWeekByCalendar(calendar);
    }

    /**
     * 获取指定Calendar的小时数
     *
     * @param calendar 已指定了时区的Calendar对象
     * @return 闭区间[0~23]
     */
    public static Integer getHourByCalendar(Calendar calendar) {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取指定时区当前的小时数
     *
     * @param hour 相对于格林威治时间偏移的小时数（用以表示时区）
     * @return 闭区间[0, 23]
     */
    public static Integer getNowHour(Integer hour) {
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.add(Calendar.HOUR, hour);
        return getHourByCalendar(calendar);
    }

    /**
     * 获取指定Calendar的分钟数
     *
     * @param calendar 已指定了时区的Calendar对象
     * @return 闭区间[0~59]
     */
    public static Integer getMinuteByCalendar(Calendar calendar) {
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 获取指定时区当前的分钟数
     *
     * @param hour 相对于格林威治时间的小时数（用以表示时区）
     * @return 闭区间[0, 59]
     */
    public static Integer getNowMinute(Integer hour) {
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.add(Calendar.HOUR, hour);
        return getMinuteByCalendar(calendar);
    }

    /**
     * 获取指定Calendar的秒数
     *
     * @param calendar 已指定了时区的Calendar对象
     * @return 闭区间[0~59]
     */
    public static Integer getSecondByCalendar(Calendar calendar) {
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 获取指定时区当前的秒数
     *
     * @param hour 相当于格林威治时间的小时数（用以表示时区）
     * @return 闭区间[0, 59]
     */
    public static Integer getNowSecond(Integer hour) {
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.add(Calendar.HOUR, hour);
        return getSecondByCalendar(calendar);
    }

    /**
     * 获取指定Calendar的毫秒数
     *
     * @param calendar 已指定了时区的Calendar对象
     * @return 闭区间[0~999]
     */
    public static Integer getMillisecondByCalendar(Calendar calendar) {
        return calendar.get(Calendar.MILLISECOND);
    }

    /**
     * 获取指定时区当前的毫秒数
     *
     * @param hour 相当于格林威治时间的小时数（用以表示时区）
     * @return 闭区间[0, 999]
     */
    public static Integer getNowMillisecond(Integer hour) {
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.add(Calendar.HOUR, hour);
        return getMillisecondByCalendar(calendar);
    }

    /**
     * 获取指定Calendar当月的最大号数
     *
     * @param calendar 已指定了时区的Calendar对象
     * @return 闭区间[28~31]
     */
    public static Integer getMaxDayForCalendar(Calendar calendar) {
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return getDayByCalendar(calendar);
    }

    /**
     * 获取指定时区当前月的最大号数
     *
     * @param hour 相当于格林威治时间的小时数（用以表示时区）
     * @return 闭区间[28~31]
     */
    public static Integer getNowMaxDay(Integer hour) {
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.add(Calendar.HOUR, hour);
        return getMaxDayForCalendar(calendar);
    }
}

