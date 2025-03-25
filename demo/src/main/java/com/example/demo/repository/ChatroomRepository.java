package com.example.demo.repository;

import com.example.demo.model.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

/**
 * ChatroomRepository provides access to chatroom records.
 * Supports lookup by topic, groupId, and week date range.
 */
@Repository
public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {

    Optional<Chatroom> findByTopicAndGroupIdAndWeekStartAndWeekEnd(
            String topic,
            String group_id,
            LocalDate week_start,
            LocalDate week_end);
}
