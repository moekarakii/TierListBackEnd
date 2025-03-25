package com.example.demo.controller;

import com.example.demo.model.Chatroom;
import com.example.demo.model.ChatMessage;
import com.example.demo.service.ChatMessageService;
import com.example.demo.service.ChatroomService;
import com.example.demo.model.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * ChatroomController handles API endpoints for retrieving chat history.
 */
@RestController
@RequestMapping("/api/chatroom")
public class ChatroomController {

    private final ChatroomService chatroomService;
    private final ChatMessageService chatMessageService;

    @Autowired
    public ChatroomController(ChatroomService chatroomService, ChatMessageService chatMessageService) {
        this.chatroomService = chatroomService;
        this.chatMessageService = chatMessageService;
    }

    /**
     * Retrieves chat history for a specific topic + groupId for the current week.
     */
    @GetMapping("/history")
    public List<ChatMessage> getChatHistory(
            @RequestParam String topic,
            @RequestParam String groupId
    ) {
        // Convert week boundaries to LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate weekStart = LocalDate.parse(Time.getCurrentBeginningOfWeek(), formatter);
        LocalDate weekEnd = LocalDate.parse(Time.getCurrentEndOfWeek(), formatter);

        // Get the corresponding chatroom
        Chatroom chatroom = chatroomService.getOrCreateChatroom(topic, groupId);

        // Return messages for that chatroom
        return chatMessageService.getMessagesByChatroom(chatroom);
    }
}

