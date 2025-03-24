package com.example.demo.service;

import com.example.demo.model.EmbeddingResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TierComparisonService {

    private final OpenAIEmbeddingsService embeddingsService;

    public TierComparisonService(OpenAIEmbeddingsService embeddingsService) {
        this.embeddingsService = embeddingsService;
    }

    /**
     * Compare two tier lists by generating embeddings and computing similarity.
     */
    public double compareTierLists(
            Map<String, List<String>> user1Tiers,
            Map<String, List<String>> user2Tiers,
            String category) {
        // Convert each user's tier list to text
        String user1Input = tierListToString(user1Tiers, category);
        String user2Input = tierListToString(user2Tiers, category);

        // Get embeddings
        List<Double> embedding1 = getEmbeddingVector(user1Input);
        List<Double> embedding2 = getEmbeddingVector(user2Input);

        // Compare with cosine similarity
        return cosineSimilarity(embedding1, embedding2);
    }

    public List<Double> getEmbeddingVector(String input) {
        EmbeddingResponse resp = embeddingsService.getEmbedding(input);
        // For simplicity, assume there's exactly one data item
        return resp.getData().get(0).getEmbedding();
    }

    private String tierListToString(Map<String, List<String>> tierList, String category) {
        // Example: "Fruits S Tier: Apple, Orange | A Tier: Banana | ..."
        StringBuilder sb = new StringBuilder(category).append(" ");
        tierList.forEach((tierName, items) -> {
            sb.append(tierName).append(": ")
                    .append(String.join(", ", items))
                    .append(" | ");
        });
        return sb.toString();
    }

    public double cosineSimilarity(List<Double> vec1, List<Double> vec2) {
        if (vec1.size() != vec2.size()) {
            throw new IllegalArgumentException("Vectors must be the same size");
        }
        double dot = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < vec1.size(); i++) {
            double a = vec1.get(i);
            double b = vec2.get(i);
            dot += a * b;
            normA += a * a;
            normB += b * b;
        }
        return dot / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}
