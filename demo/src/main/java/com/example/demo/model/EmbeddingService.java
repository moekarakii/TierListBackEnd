package com.example.demo.model;

import java.util.ArrayList;

public class EmbeddingService {
    private ArrayList<Integer> Embeddings;

    public EmbeddingService() {
        this.Embeddings = new ArrayList<Integer>();
    }

    public EmbeddingService(ArrayList<Integer> embeddings) {
        this.Embeddings = embeddings;
    }

    public ArrayList<Integer> getEmbeddings() {
        return this.Embeddings;
    }

    public void setEmbeddings(ArrayList<Integer> embeddings) {
        this.Embeddings = embeddings;
    }

    public void addEmbedding(Integer embedding) {
        this.Embeddings.add(embedding);
    }

    public void removeEmbedding(Integer embedding) {
        this.Embeddings.remove(embedding);
    }

    public void clearEmbeddings() {
        this.Embeddings.clear();
    }

    public void printEmbeddings() {
        for (Integer embedding : this.Embeddings) {
            System.out.println(embedding);
        }
    }
}
