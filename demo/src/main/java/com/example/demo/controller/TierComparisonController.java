package com.example.demo.controller;

import com.example.demo.service.TierComparisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tiers")
public class TierComparisonController {

    private final TierComparisonService tierComparisonService;

    @Autowired
    public TierComparisonController(TierComparisonService tierComparisonService) {
        this.tierComparisonService = tierComparisonService;
    }

    /**
     * A DTO for the request body, e.g.:
     * {
     * "user1Tiers": {
     * "S Tier": ["Apple", "Orange"],
     * "A Tier": ["Banana"]
     * },
     * "user2Tiers": {
     * "S Tier": ["Apple", "Orange"],
     * "A Tier": ["Banana"]
     * },
     * "category": "Fruits"
     * }
     */
    public static class CompareRequest {
        private Map<String, List<String>> user1Tiers;
        private Map<String, List<String>> user2Tiers;
        private String category;

        public Map<String, List<String>> getUser1Tiers() {
            return user1Tiers;
        }

        public void setUser1Tiers(Map<String, List<String>> user1Tiers) {
            this.user1Tiers = user1Tiers;
        }

        public Map<String, List<String>> getUser2Tiers() {
            return user2Tiers;
        }

        public void setUser2Tiers(Map<String, List<String>> user2Tiers) {
            this.user2Tiers = user2Tiers;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }
    }

    /**
     * A DTO for the response, e.g.:
     * {
     * "similarity": 0.98765
     * }
     */
    public static class CompareResponse {
        private double similarity;

        public CompareResponse(double similarity) {
            this.similarity = similarity;
        }

        public double getSimilarity() {
            return similarity;
        }
    }

    @PostMapping("/compare")
    public CompareResponse compareTiers(@RequestBody CompareRequest request) {
        double similarity = tierComparisonService.compareTierLists(
                request.getUser1Tiers(),
                request.getUser2Tiers(),
                request.getCategory());
        return new CompareResponse(similarity);
    }
}
