package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * ChatMessage entity stores individual messages sent
 * inside a chatroom by a user at a specific time.
 */
@Entity
@Table(name = "chatMessage")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "chatroom_id", nullable = false)
    private Chatroom chatroom;

    private Long userId;

    private String message;

    private LocalDateTime timestamp;

    public ChatMessage() {}

    public ChatMessage(Chatroom chatroom, Long userId, String message, LocalDateTime timestamp) {
        this.chatroom = chatroom;
        this.userId = userId;
        this.message = message;
        this.timestamp = timestamp;
    }

    public Long getMessageId() {
        return messageId;
    }

    public Chatroom getChatroom() {
        return chatroom;
    }

    public void setChatroom(Chatroom chatroom) {
        this.chatroom = chatroom;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
