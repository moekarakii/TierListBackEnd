package com.example.demo.model;

import jakarta.persistence.*;

/**
 * This class represents an entry in a Tier List.
 * Each entry is associated with a user and a tier category.
 */
@Entity
@Table(name = "tierEntries")
public class TierList {

    // Primary key for each entry
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer entryId;

    // ID of the user who created this entry
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    // ID of the tier category this entry belongs to
    @Column(name = "tier_id", nullable = false)
    private Integer tierId;

    // Name or content of the entry (e.g., a movie or game title)
    @Column(nullable = false)
    private String entry;

    // Tier ranking value (e.g., S/A/B/C), stored as integer or byte
    @Column(nullable = false)
    private Integer tier;

    // Default constructor
    public TierList() {}

    // Constructor with parameters
    public TierList(Integer userId, Integer tierId, String entry, Integer tier) {
        this.userId = userId;
        this.tierId = tierId;
        this.entry = entry;
        this.tier = tier;
    }

    // Getter and Setter methods

    public Integer getEntryId() {
        return entryId;
    }

    public void setEntryId(Integer entryId) {
        this.entryId = entryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTierId() {
        return tierId;
    }

    public void setTierId(Integer tierId) {
        this.tierId = tierId;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }
}