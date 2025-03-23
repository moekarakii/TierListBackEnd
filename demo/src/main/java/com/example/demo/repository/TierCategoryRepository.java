package com.example.demo.repository;

import com.example.demo.model.TierCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TierCategoryRepository extends JpaRepository<TierCategory, Long>{

}
