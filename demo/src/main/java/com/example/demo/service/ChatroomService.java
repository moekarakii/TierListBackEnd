package com.example.demo.service;

import com.example.demo.model.Chatroom;
import com.example.demo.repository.ChatroomRepository;
import com.example.demo.model.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * ChatroomService handles chatroom creation and lookup
 * based on topic, groupId, and current week boundaries.
 */
@Service
public class ChatroomService {

    private final ChatroomRepository chatroomRepository;

    @Autowired
    public ChatroomService(ChatroomRepository chatroomRepository) {
        this.chatroomRepository = chatroomRepository;
    }

    /**
     * Finds or creates a chatroom based on topic and groupId
     * for the current week's date range.
     */
    public Chatroom getOrCreateChatroom(String topic, String groupId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate weekStart = LocalDate.parse(Time.getCurrentBeginningOfWeek(), formatter);
        LocalDate weekEnd = LocalDate.parse(Time.getCurrentEndOfWeek(), formatter);

        Optional<Chatroom> existing = chatroomRepository
                .findByTopicAndGroupIdAndWeekStartAndWeekEnd(topic, groupId, weekStart, weekEnd);

        return existing.orElseGet(() -> {
            Chatroom newRoom = new Chatroom(topic, groupId, weekStart, weekEnd);
            return chatroomRepository.save(newRoom);
        });
    }
}
