package com.example.demo.service;

import com.example.demo.model.TierList;
import com.example.demo.repository.TierListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This service handles all business logic related to Tier List entries.
 * It provides methods to create, retrieve, and delete entries based on user or category.
 */
@Service
public class TierListService {

    private final TierListRepository tierListRepository;

    public TierListService(TierListRepository tierListRepository) {
        this.tierListRepository = tierListRepository;
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
    public List<TierList> getEntriesByTierId(Integer tierId) {
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
}