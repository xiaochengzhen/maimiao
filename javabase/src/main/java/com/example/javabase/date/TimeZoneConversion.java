package com.example.javabase.date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class TimeZoneConversion {

    /**
     * @description  时区转换
     * @author xiaobo
     * @date 2024/2/23 13:06
     */
    public static LocalDateTime timeTransFormLocale1(long time, String timeZone) {
        // 时间戳转utcDateTime
        LocalDateTime utcDateTime = Instant.ofEpochMilli(time).atZone(ZoneOffset.UTC).toLocalDateTime();
        ZoneId targetZoneId = ZoneId.of(timeZone);
        return utcDateTime.atZone(ZoneOffset.UTC).withZoneSameInstant(targetZoneId).toLocalDateTime();
    }

    public static LocalDateTime timeTransFormLocale2(long time, String timeZone) {
        Instant instant = Instant.ofEpochMilli(time);
        // 将 Instant 转换为 LocalDateTime，并指定目标时区
        ZoneId targetZoneId = ZoneId.of(timeZone);
        return LocalDateTime.ofInstant(instant, targetZoneId);
    }
}
