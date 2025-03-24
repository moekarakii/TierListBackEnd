package com.example.demo.controller;

import com.example.demo.model.TierList;
import com.example.demo.service.TierListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * This controller handles all HTTP requests related to tier list entries.
 * It allows the frontend to create, fetch, and delete entries.
 */
@RestController
@RequestMapping("/api/entries")
@CrossOrigin(origins = "http://localhost:5173") // Allow frontend access
public class TierListController {

    private final TierListService tierListService;

    public TierListController(TierListService tierListService) {
        this.tierListService = tierListService;
    }

    // Create a new tier list entry
    @PostMapping
    public TierList createEntry(@RequestBody TierList tierList) {
        return tierListService.createTierEntry(tierList);
    }

    // Get all entries (for debug/testing)
    @GetMapping
    public List<TierList> getAllEntries() {
        return tierListService.getAllEntries();
    }

    // Get entries by user ID
    @GetMapping("/user/{userId}")
    public List<TierList> getEntriesByUserId(@PathVariable Integer userId) {
        return tierListService.getEntriesByUserId(userId);
    }

    // Get entries by tier category ID
    @GetMapping("/tier/{tierId}")
    public List<TierList> getEntriesByTierId(@PathVariable Long tierId) {
        return tierListService.getEntriesByTierId(tierId);
    }

    // Get entries by both user ID and tier ID
    @GetMapping("/user/{userId}/tier/{tierId}")
    public List<TierList> getEntriesByUserIdAndTierId(
            @PathVariable Integer userId,
            @PathVariable Integer tierId) {
        return tierListService.getEntriesByUserIdAndTierId(userId, tierId);
    }

    // Delete an entry by ID
    @DeleteMapping("/{entryId}")
    public String deleteEntryById(@PathVariable Integer entryId) {
        boolean deleted = tierListService.deleteEntryById(entryId);
        return deleted ? "Entry deleted successfully" : "Entry not found";
    }

    /**
     * Get all tier list entries for the current week,
     * grouped by user ID.
     */
    @GetMapping("/week")
    public Map<Integer, List<TierList>> getEntriesGroupedByUserThisWeek() {
        return tierListService.getCurrentWeekEntriesGroupedByUser();
    }

}