package com.example.demo.service;

import java.util.List;
import com.example.demo.model.TierCategory;
import com.example.demo.repository.TierCategoryRepository;

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
    

}
