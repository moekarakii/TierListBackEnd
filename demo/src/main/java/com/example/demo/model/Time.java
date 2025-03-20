package com.example.demo.model;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

public class Time {
    public static String getDate() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.toString(); // 2023-11-03
    }

    public static String getTime() {
        LocalTime currentTime = LocalTime.now();
        return currentTime.toString(); // 10:15:30.123456789
    }

    public static String getDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return currentDateTime.toString(); // 2023-11-03T10:15:30.123456789
    }

    public static String getCurrentBeginningOfWeek() {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        return startOfWeek.toString(); // 2023-10-15
    }

    public static String getCurrentEndOfWeek() {
        LocalDate today = LocalDate.now();
        LocalDate endOfWeek = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
        return endOfWeek.toString(); // 2023-10-21
    }
}
