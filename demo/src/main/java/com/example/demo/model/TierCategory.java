package com.example.demo.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tierCategory")
public class TierCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tierId;

    private String subject;

    private LocalDateTime start_time;
    private LocalDateTime end_time;

    // Default constructor
    public TierCategory() {}

    // Constructor with parameters
    public TierCategory(Integer tierId, String subject, LocalDateTime start_time, LocalDateTime end_time) {
        this.tierId = tierId;
        this.subject = subject;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    // Getters and Setters

    public Integer getTierId() {
        return tierId;
    }

    public void setTierId(Integer tierId) {
        this.tierId = tierId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDateTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalDateTime start_time) {
        this.start_time = start_time;
    }

    public LocalDateTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalDateTime end_time) {
        this.end_time = end_time;
    }
}