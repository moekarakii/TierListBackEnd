package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tierCategory") // Matches exactly the DB table name
public class TierCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tierId") // Matches exactly the DB column name
    private Long tierId;

    @Column(name = "subject") // Matches exactly the DB column name
    private String subject;

    @Column(name = "startTime") // Matches exactly the DB column name
    private LocalDate startTime;

    @Column(name = "endTime") // Matches exactly the DB column name
    private LocalDate endTime;

    // --- Constructors ---
    public TierCategory() {
    }

    public TierCategory(Long tierId, String subject, LocalDate startTime, LocalDate endTime) {
        this.tierId = tierId;
        this.subject = subject;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // --- Getters and Setters ---
    public Long getTierId() {
        return tierId;
    }

    public void setTierId(Long tierId) {
        this.tierId = tierId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    // --- toString() ---
    @Override
    public String toString() {
        return "TierCategory{" +
                "tierId=" + tierId +
                ", subject='" + subject + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
