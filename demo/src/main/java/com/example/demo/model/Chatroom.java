package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Chatroom entity represents a group-based chatroom
 * for users who submitted similar Tier Lists for a given topic and week.
 */
@Entity
@Table(name = "chatroom")
public class Chatroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatroomId;

    private String topic;
    private String groupId;       // if its in integer by design, update it later
    private LocalDate weekStart;  //
    private LocalDate weekEnd;    //

    public Chatroom() {}

    public Chatroom(String topic, String groupId, LocalDate weekStart, LocalDate weekEnd) {
        this.topic = topic;
        this.groupId = groupId;
        this.weekStart = weekStart;
        this.weekEnd = weekEnd;
    }

    public Long getChatroomId() {
        return chatroomId;
    }

    public void setChatroomId(Long chatroomId) {
        this.chatroomId = chatroomId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public LocalDate getWeekStart() {
        return weekStart;
    }

    public void setWeekStart(LocalDate weekStart) {
        this.weekStart = weekStart;
    }

    public LocalDate getWeekEnd() {
        return weekEnd;
    }

    public void setWeekEnd(LocalDate weekEnd) {
        this.weekEnd = weekEnd;
    }
}

