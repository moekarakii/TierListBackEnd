package com.example.demo.model;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

public class Time {
    public static String getDate() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.toString(); // 2023-11-03
    }

    public static String getCurrentBeginningOfWeek() {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        return startOfWeek.toString(); // 2023-10-15
    }
}
