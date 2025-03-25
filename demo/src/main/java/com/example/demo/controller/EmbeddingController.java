package com.example.demo.controller;

import com.example.demo.model.TierList;
import com.example.demo.service.EmbeddingsService;
import com.example.demo.service.TierListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/groups")
public class EmbeddingController {

    private final TierListService tierListService;
    private final EmbeddingsService embeddingsService;

    public EmbeddingController(TierListService tierListService, EmbeddingsService embeddingsService) {
        this.tierListService = tierListService;
        this.embeddingsService = embeddingsService;
    }

    /**
     * Returns groups of users based on similarity.
     * The 'threshold' query parameter defines the cosine similarity required for
     * grouping (default is 0.85).
     */
    @GetMapping
    public List<EmbeddingsService.GroupResponse> getGroupedUsers(
            @RequestParam(defaultValue = "0.85") double threshold) {
        // Retrieves the current week’s entries grouped by user.
        Map<Integer, List<TierList>> entriesByUser = tierListService.getCurrentWeekEntriesGroupedByUser();
        // Uses the embeddings service to group users and return DTOs with group
        // numbers.
        return embeddingsService.getGroupedUserResponses(entriesByUser, threshold);
    }

    /**
     * Returns the current week’s entries grouped by user.
     */
    @GetMapping("/entries")
    public Map<Integer, List<TierList>> getCurrentWeekEntriesGroupedByUser() {
        return tierListService.getCurrentWeekEntriesGroupedByUser();
    }

    /**
     * Returns the current user’s entries.
     */
    @GetMapping("/{userId}/entries")
    public List<TierList> getCurrentUserEntries(@RequestParam Integer userId) {
        return tierListService.getEntriesByUserId(userId);
    }
}
