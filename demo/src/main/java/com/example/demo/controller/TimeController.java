package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Time;

@RestController
public class TimeController {
    @GetMapping("/timer")
    public Map<String, String> getCurrentWeek(){
        Map<String, String> response = new HashMap<>();

        response.put("startOfWeek", Time.getCurrentBeginningOfWeek());
        response.put("endOfWeek", Time.getCurrentEndOfWeek());

        return response;
    
    }




}
