package com.example.demo.model;

import java.util.List;

/**
 * Represents the response from the OpenAI Embeddings API.
 * Example JSON structure:
 * {
 * "object": "list",
 * "data": [
 * {
 * "object": "embedding",
 * "embedding": [... array of floats/doubles ...],
 * "index": 0
 * }
 * ],
 * "model": "text-embedding-ada-002",
 * "usage": {
 * "prompt_tokens": 5,
 * "total_tokens": 5
 * }
 * }
 */
public class EmbeddingResponse {

    private String object; // e.g. "list"
    private List<Data> data; // The list of embeddings
    private String model; // e.g. "text-embedding-ada-002"
    private Usage usage; // Token usage info

    public EmbeddingResponse() {
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    // Nested class for "data" items
    public static class Data {
        private String object; // e.g. "embedding"
        private List<Double> embedding;
        private int index;

        public Data() {
        }

        public String getObject() {
            return object;
        }

        public void setObject(String object) {
            this.object = object;
        }

        public List<Double> getEmbedding() {
            return embedding;
        }

        public void setEmbedding(List<Double> embedding) {
            this.embedding = embedding;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }

    // Nested class for usage info
    public static class Usage {
        private int prompt_tokens;
        private int total_tokens;

        public Usage() {
        }

        public int getPrompt_tokens() {
            return prompt_tokens;
        }

        public void setPrompt_tokens(int prompt_tokens) {
            this.prompt_tokens = prompt_tokens;
        }

        public int getTotal_tokens() {
            return total_tokens;
        }

        public void setTotal_tokens(int total_tokens) {
            this.total_tokens = total_tokens;
        }
    }
}
