package com.example.demo.repository;

import com.example.demo.model.ChatMessage;
import com.example.demo.model.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ChatMessageRepository handles database access
 * for messages inside a chatroom.
 */
@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    /**
     * Finds all messages for a specific chatroom, ordered by timestamp ascending.
     */
    List<ChatMessage> findByChatroomOrderByTimestampAsc(Chatroom chatroom);
}

