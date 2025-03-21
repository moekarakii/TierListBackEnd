package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.TierCategory;
import com.example.demo.repository.TierCategoryRepository;

import jakarta.annotation.PostConstruct;

@Service
public class TierCategoryService {
     private final TierCategoryRepository tierCategoryRepository;

    public TierCategoryService(TierCategoryRepository tierCategoryRepository) {
        this.tierCategoryRepository = tierCategoryRepository;
    }

    public List<TierCategory> getAllTiers() {
        return tierCategoryRepository.findAll();
    }

    public TierCategory createTier(TierCategory tier) {
        return tierCategoryRepository.save(tier);
    }
    
      @PostConstruct
    public void printAllTiersAtStartup() {
        List<TierCategory> tiers = tierCategoryRepository.findAll();
        System.out.println("===== Current Tiers in DB =====");
        for (TierCategory tier : tiers) {
            System.out.println(tier);
        }
        System.out.println("================================");
    }

}
