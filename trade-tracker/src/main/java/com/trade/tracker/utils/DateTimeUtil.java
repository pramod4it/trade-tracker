package com.trade.tracker.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMuuuu");

    public static String formatDate(LocalDateTime dateTime) {
        return dateTime.format(formatter);
    }
}
