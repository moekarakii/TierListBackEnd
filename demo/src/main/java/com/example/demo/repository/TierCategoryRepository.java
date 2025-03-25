package com.example.demo.repository;

import com.example.demo.model.TierCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.Optional;

public interface TierCategoryRepository extends JpaRepository<TierCategory, Long> {
    @Query("SELECT t FROM TierCategory t WHERE t.start_time = :startTime AND t.end_time = :endTime")
    Optional<TierCategory> findByDateRange(
            @Param("startTime") LocalDate startTime,
            @Param("endTime") LocalDate endTime);
}
