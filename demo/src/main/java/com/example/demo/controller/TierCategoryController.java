package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TierCategory;
import com.example.demo.service.TierCategoryService;
@RestController
@RequestMapping("/api/tiers")
public class TierCategoryController {
    private final TierCategoryService tierCategoryService;


    public TierCategoryController(TierCategoryService tierCategoryService) {
        this.tierCategoryService = tierCategoryService;
    }
    // === GET: Fetch all tier categories ===
    @GetMapping
    public List<TierCategory> getAllTiers() {
        return tierCategoryService.getAllTiers();
    }
    // === POST: Create new tier category ===
    @PostMapping
    public TierCategory createTier(@RequestBody TierCategory tier) {
        return tierCategoryService.createTier(tier);
    }
    @GetMapping("/debug/print")
    public String printTiers() {
        List<TierCategory> tiers = tierCategoryService.getAllTiers();
        System.out.println("===== Current Tiers in DB =====");
        for (TierCategory tier : tiers) {
            System.out.println(tier);
        }
        System.out.println("================================");
        return "Check Heroku logs for DB contents!";
    }
}
