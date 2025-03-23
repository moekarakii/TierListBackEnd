package com.example.demo.service;

import com.example.demo.model.EmbeddingResponse;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TierComparisonService {
    private final OpenAIEmbeddingsService embeddingsService;

    public TierComparisonService(OpenAIEmbeddingsService embeddingsService) {
        this.embeddingsService = embeddingsService;
    }

    /**
     * Takes a user's tier list, gets the embeddings, and returns the vector.
     */
    public List<Double> getTierEmbedding(List<String> tierEntries, String category) {
        // You might concatenate or format the tiers + category into a single string
        String combinedInput = String.join(", ", tierEntries) + " - " + category;
        EmbeddingResponse response = embeddingsService.getEmbedding(combinedInput);

        // Assume the response has a single data item
        return response.getData().get(0).getEmbedding();
    }

    /**
     * Compares two embedding vectors and returns a similarity score or boolean.
     */
    public double compareEmbeddings(List<Double> embedding1, List<Double> embedding2) {
        // Example: Cosine similarity or some other metric
        return cosineSimilarity(embedding1, embedding2);
    }

    private double cosineSimilarity(List<Double> vec1, List<Double> vec2) {
        // Very rough example
        double dot = 0.0;
        double norm1 = 0.0;
        double norm2 = 0.0;

        for (int i = 0; i < vec1.size(); i++) {
            dot += vec1.get(i) * vec2.get(i);
            norm1 += vec1.get(i) * vec1.get(i);
            norm2 += vec2.get(i) * vec2.get(i);
        }
        return dot / (Math.sqrt(norm1) * Math.sqrt(norm2));
    }
}
