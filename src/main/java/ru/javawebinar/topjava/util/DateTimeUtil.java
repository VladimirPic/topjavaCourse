package ru.javawebinar.topjava.util;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static <T extends Comparable<T>> boolean isBetweenHalfOpen(T checkValue, T start, T end) {
        return checkValue.compareTo(start) >= 0 && checkValue.compareTo(end) <= 0;
    }

    public static @Nullable LocalDate parseLocalDate(@Nullable String localDate) {
        return StringUtils.isEmpty(localDate) ? null : LocalDate.parse(localDate);
    }

    public static @Nullable LocalTime parseLocalTime(@Nullable String localTime) {
        return StringUtils.isEmpty(localTime) ? null : LocalTime.parse(localTime);
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}

