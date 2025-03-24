package com.example.demo.repository;

import com.example.demo.model.TierCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface TierCategoryRepository extends JpaRepository<TierCategory, Long>{
    // Use @Query to match actual field names: start_time, end_time
    @Query("SELECT t FROM TierCategory t WHERE t.start_time = :start AND t.end_time = :end")
    Optional<TierCategory> findByStart_timeAndEnd_time(@Param("start") LocalDate start,
                                                       @Param("end") LocalDate end);
}

