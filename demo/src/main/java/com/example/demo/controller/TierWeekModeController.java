package com.example.demo.controller;

import com.example.demo.service.TierWeekModeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TierWeekModeController {

    private final TierWeekModeService tierWeekModeService;

    public TierWeekModeController(TierWeekModeService tierWeekModeService) {
        this.tierWeekModeService = tierWeekModeService;
    }

    @GetMapping("/tierMode")
    public String getCurrentMode() {
        return tierWeekModeService.getCurrentMode().name();
    }
}
