package com.example.demo.model;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tierCategory")
public class TierCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  
    @Column(name = "tier_id")
    private Long tierId;

    @Column(name = "subject")
    private String subject;

    @Column(name = "start_time")
    private LocalDate start_time;
    
    @Column(name = "end_time")
    private LocalDate end_time;

    // Default constructor
    public TierCategory() {}

    // Constructor with parameters
    public TierCategory(Long tierId, String subject, LocalDate start_time, LocalDate end_time) {
        this.tierId = tierId;
        this.subject = subject;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    // Getters and Setters

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

    public LocalDate getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalDate start_time) {
        this.start_time = start_time;
    }

    public LocalDate getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalDate end_time) {
        this.end_time = end_time;
    }
    @Override
    public String toString() {
        return "TierCategory{" +
                "tierId=" + tierId +
                ", subject='" + subject + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                '}';
    }
}