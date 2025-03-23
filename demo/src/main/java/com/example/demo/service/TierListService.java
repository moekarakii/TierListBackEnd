package com.example.demo.service;

import com.example.demo.model.TierList;
import com.example.demo.model.TierCategory;
import com.example.demo.model.Time;
import com.example.demo.repository.TierListRepository;
import com.example.demo.repository.TierCategoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This service handles all business logic related to Tier List entries.
 * It provides methods to create, retrieve, and delete entries based on user or category.
 */
@Service
public class TierListService {

    private final TierListRepository tierListRepository;
    private final TierCategoryRepository tierCategoryRepository;

    public TierListService(TierListRepository tierListRepository, TierCategoryRepository tierCategoryRepository) {
        this.tierListRepository = tierListRepository;
        this.tierCategoryRepository = tierCategoryRepository;
    }

    // Create a new tier list entry
    public TierList createTierEntry(TierList tierList) {
        return tierListRepository.save(tierList);
    }

    // Retrieve all tier list entries
    public List<TierList> getAllEntries() {
        return tierListRepository.findAll();
    }

    // Get all entries created by a specific user
    public List<TierList> getEntriesByUserId(Integer userId) {
        return tierListRepository.findByUserId(userId);
    }

    // Get all entries under a specific tier category
    public List<TierList> getEntriesByTierId(Long tierId) {
        return tierListRepository.findByTierId(tierId);
    }

    // Get all entries by a user in a specific tier category
    public List<TierList> getEntriesByUserIdAndTierId(Integer userId, Integer tierId) {
        return tierListRepository.findByUserIdAndTierId(userId, tierId);
    }

    // Delete an entry by its ID
    public boolean deleteEntryById(Integer entryId) {
        Optional<TierList> existing = tierListRepository.findById(entryId);
        if (existing.isPresent()) {
            tierListRepository.deleteById(entryId);
            return true;
        }
        return false;
    }

    /**
     * Get all tier list entries for the current week,
     * grouped by user ID.
     */
    public Map<Integer, List<TierList>> getCurrentWeekEntriesGroupedByUser() {
        // Get the current week's start and end date
        LocalDate start = LocalDate.parse(Time.getCurrentBeginningOfWeek());
        LocalDate end = LocalDate.parse(Time.getCurrentEndOfWeek());

        // Find the tier category that matches this week's range
        Optional<TierCategory> categoryOptional = tierCategoryRepository.findByStart_timeAndEnd_time(start, end);
        if (categoryOptional.isEmpty()) {
            return new HashMap<>(); // No category found for this week
        }

        TierCategory category = categoryOptional.get();

        // Find all entries under this week's tier category
        List<TierList> allEntries = tierListRepository.findByTierId(category.getTierId());

        // Group entries by user ID
        return allEntries.stream()
                .collect(Collectors.groupingBy(TierList::getUserId));
    }
}
