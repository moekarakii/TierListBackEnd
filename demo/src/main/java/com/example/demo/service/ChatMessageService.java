package com.example.demo.service;

import com.example.demo.model.ChatMessage;
import com.example.demo.model.Chatroom;
import com.example.demo.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ChatMessageService manages storing and retrieving
 * chat messages for a given chatroom.
 */
@Service
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    @Autowired
    public ChatMessageService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    /**
     * Saves a new message to the given chatroom.
     */
    public ChatMessage saveMessage(Chatroom chatroom, Long userId, String messageContent) {
        ChatMessage message = new ChatMessage(
                chatroom,
                userId,
                messageContent,
                LocalDateTime.now()
        );
        return chatMessageRepository.save(message);
    }

    /**
     * Retrieves all messages from a chatroom, sorted by timestamp ascending.
     */
    public List<ChatMessage> getMessagesByChatroom(Chatroom chatroom) {
        return chatMessageRepository.findByChatroomOrderByTimestampAsc(chatroom);
    }
}

