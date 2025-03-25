package com.example.demo.websocket;

import com.example.demo.model.Chatroom;
import com.example.demo.service.ChatMessageService;
import com.example.demo.service.ChatroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.time.LocalDateTime;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * ChatSocketHandler handles WebSocket connections, message broadcasting,
 * and message persistence. Users are grouped by topic and groupId.
 */
@Component
public class ChatSocketHandler extends TextWebSocketHandler {

    private final ChatroomService chatroomService;
    private final ChatMessageService chatMessageService;

    // Maps roomKey (topic + groupId) to connected WebSocket sessions
    private final Map<String, Set<WebSocketSession>> chatrooms = new ConcurrentHashMap<>();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public ChatSocketHandler(ChatroomService chatroomService, ChatMessageService chatMessageService) {
        this.chatroomService = chatroomService;
        this.chatMessageService = chatMessageService;
    }

    /**
     * Triggered when a client establishes a WebSocket connection.
     * Adds the session to the appropriate chatroom group.
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String topic = getParam(session, "topic");
        String groupId = getParam(session, "groupId");
        String roomKey = topic + "_" + groupId;

        chatrooms.computeIfAbsent(roomKey, k -> ConcurrentHashMap.newKeySet()).add(session);
        System.out.println("User joined chatroom: " + roomKey);
    }

    /**
     * Handles an incoming message.
     * Parses message JSON, saves it to the database, and broadcasts it to the group.
     */
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        String topic = getParam(session, "topic");
        String groupId = getParam(session, "groupId");
        String roomKey = topic + "_" + groupId;

        // Parse message payload from JSON
        JsonNode messageJson = objectMapper.readTree(textMessage.getPayload());
        Long userId = messageJson.get("userId").asLong();
        String messageContent = messageJson.get("message").asText();

        // Get or create the chatroom
        Chatroom chatroom = chatroomService.getOrCreateChatroom(topic, groupId);

        // Save the message to the database
        chatMessageService.saveMessage(chatroom, userId, messageContent);

        // Build a broadcast payload
        String broadcastPayload = objectMapper.createObjectNode()
                .put("userId", userId)
                .put("message", messageContent)
                .put("timestamp", LocalDateTime.now().toString())
                .toString();

        TextMessage broadcast = new TextMessage(broadcastPayload);

        // Send the message to all sessions in the same chatroom
        Set<WebSocketSession> sessions = chatrooms.getOrDefault(roomKey, Collections.emptySet());
        for (WebSocketSession s : sessions) {
            if (s.isOpen()) {
                s.sendMessage(broadcast);
            }
        }
    }

    /**
     * Triggered when a WebSocket connection is closed.
     * Removes the session from its chatroom group.
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String topic = getParam(session, "topic");
        String groupId = getParam(session, "groupId");
        String roomKey = topic + "_" + groupId;

        Set<WebSocketSession> sessions = chatrooms.get(roomKey);
        if (sessions != null) {
            sessions.remove(session);
        }

        System.out.println("User left chatroom: " + roomKey);
    }

    /**
     * Utility method to extract a query parameter from the session URI.
     */
    private String getParam(WebSocketSession session, String key) {
        String query = Objects.requireNonNull(session.getUri()).getQuery();
        if (query != null) {
            for (String param : query.split("&")) {
                String[] parts = param.split("=");
                if (parts.length == 2 && parts[0].equals(key)) {
                    return parts[1];
                }
            }
        }
        return "";
    }
}
