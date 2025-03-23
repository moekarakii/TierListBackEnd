package com.example.demo.service;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

import org.springframework.stereotype.Service;

@Service
public class TierWeekModeService {

    public enum Mode {

        TierListWeek,
        ChatWeek
    }

    public Mode getCurrentMode(){

        LocalDate today = LocalDate.now();
        int weekNumber = today.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
        
         // Even week = Tier List Week, Odd week = Chat Week
        if (weekNumber % 2 == 0) {

            return Mode.TierListWeek;
        } 
            else {

            return Mode.ChatWeek;
        }
    }

}
