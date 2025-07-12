package com.backtest.breeze.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    public static Instant getInstant(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(dateTime, formatter);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("Asia/Kolkata"));
        return zdt.toInstant();
    }
}