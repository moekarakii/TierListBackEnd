package com.example.demo.repository;

import com.example.demo.model.TierCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.Optional;

// This interface provides built-in CRUD operations for the TierCategory entity
public interface TierCategoryRepository extends JpaRepository<TierCategory, Long> {

    // Custom method to find a TierCategory by startTime and endTime (used for
    // filtering by time range)
    Optional<TierCategory> findByStartTimeAndEndTime(LocalDate startTime, LocalDate endTime);
}
