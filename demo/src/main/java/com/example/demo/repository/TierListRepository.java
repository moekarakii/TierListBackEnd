package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.TierList;

/**
 * This interface provides database access methods for TierList (tierEntries
 * table).
 * It allows querying entries by user ID, tier category ID, or both.
 */
@Repository
public interface TierListRepository extends JpaRepository<TierList, Integer> {

    // Find all entries by a specific user
    List<TierList> findByUserId(Integer user_id);

    // Find all entries under a specific tier category
    List<TierList> findByTierId(Long tier_id);

    // Find all entries by a user in a specific category
    List<TierList> findByUserIdAndTierId(Integer user_id, Integer tier_id);
}
