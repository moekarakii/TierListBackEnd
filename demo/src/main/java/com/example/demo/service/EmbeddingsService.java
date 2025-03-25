package com.example.demo.service;

import com.example.demo.model.EmbeddingResponse;
import com.example.demo.model.TierList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmbeddingsService {

	private final OpenAIEmbeddingsService openAIEmbeddingsService;

	public EmbeddingsService(OpenAIEmbeddingsService openAIEmbeddingsService) {
		this.openAIEmbeddingsService = openAIEmbeddingsService;
	}

	public EmbeddingResponse getEmbedding(String input) {
		return openAIEmbeddingsService.getEmbedding(input);
	}

	/**
	 * Groups users by the similarity of their embedding vectors using a union-find
	 * algorithm.
	 * 
	 * @param entriesByUser A map where the key is the user ID and the value is a
	 *                      list of TierList entries.
	 * @param threshold     The cosine similarity threshold to determine if two
	 *                      users are similar.
	 * @return A map where each key is the representative user ID of a group and the
	 *         value is the list of user IDs in that group.
	 */
	public Map<Integer, List<Integer>> groupUsersBySimilarity(Map<Integer, List<TierList>> entriesByUser,
			double threshold) {
		Map<Integer, List<Double>> userEmbeddings = new HashMap<>();
		for (Map.Entry<Integer, List<TierList>> entry : entriesByUser.entrySet()) {
			Integer userId = entry.getKey();
			List<TierList> tierLists = entry.getValue();
			String inputText = convertTierListsToText(tierLists);
			List<Double> embedding = getEmbedding(inputText).getData().get(0).getEmbedding();
			userEmbeddings.put(userId, embedding);
		}

		// Initialize union-find structure: each user is its own parent initially.
		Map<Integer, Integer> parent = new HashMap<>();
		for (Integer userId : userEmbeddings.keySet()) {
			parent.put(userId, userId);
		}

		// Compare each pair of users and union their groups if similarity >= threshold.
		List<Integer> userIds = new ArrayList<>(userEmbeddings.keySet());
		for (int i = 0; i < userIds.size(); i++) {
			Integer userId1 = userIds.get(i);
			for (int j = i + 1; j < userIds.size(); j++) {
				Integer userId2 = userIds.get(j);
				double similarity = cosineSimilarity(userEmbeddings.get(userId1), userEmbeddings.get(userId2));
				if (similarity >= threshold) {
					union(userId1, userId2, parent);
				}
			}
		}

		// Build groups from the union-find structure.
		Map<Integer, List<Integer>> groups = new HashMap<>();
		for (Integer userId : userEmbeddings.keySet()) {
			int root = find(userId, parent);
			groups.computeIfAbsent(root, k -> new ArrayList<>()).add(userId);
		}
		return groups;
	}

	/**
	 * Returns a list of group responses, each with a sequential group number and
	 * its member user IDs.
	 *
	 * @param entriesByUser A map of user entries.
	 * @param threshold     The similarity threshold.
	 * @return A list of GroupResponse DTOs.
	 */
	public List<GroupResponse> getGroupedUserResponses(Map<Integer, List<TierList>> entriesByUser, double threshold) {
		Map<Integer, List<Integer>> groups = groupUsersBySimilarity(entriesByUser, threshold);
		List<GroupResponse> groupResponses = new ArrayList<>();
		int groupNumber = 1;
		for (Map.Entry<Integer, List<Integer>> entry : groups.entrySet()) {
			groupResponses.add(new GroupResponse(groupNumber++, entry.getValue()));
		}
		return groupResponses;
	}

	// Helper method to convert TierList entries to text.
	private String convertTierListsToText(List<TierList> tierLists) {
		StringBuilder sb = new StringBuilder();
		for (TierList entry : tierLists) {
			sb.append("TierCategoryID: ").append(entry.getTierId()).append(" ");
			sb.append("TierRank: ").append(entry.getTier()).append(" ");
			sb.append("Entry: ").append(entry.getEntry()).append(" | ");
		}
		return sb.toString();
	}

	// Cosine similarity implementation.
	private double cosineSimilarity(List<Double> vec1, List<Double> vec2) {
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

	// Union-find helper methods.
	private int find(Integer userId, Map<Integer, Integer> parent) {
		if (!parent.get(userId).equals(userId)) {
			parent.put(userId, find(parent.get(userId), parent));
		}
		return parent.get(userId);
	}

	private void union(Integer userId1, Integer userId2, Map<Integer, Integer> parent) {
		int root1 = find(userId1, parent);
		int root2 = find(userId2, parent);
		if (root1 != root2) {
			parent.put(root2, root1);
		}
	}

	// DTO to expose group information.
	public static class GroupResponse {
		private int groupNumber;
		private List<Integer> userIds;

		public GroupResponse(int groupNumber, List<Integer> userIds) {
			this.groupNumber = groupNumber;
			this.userIds = userIds;
		}

		public int getGroupNumber() {
			return groupNumber;
		}

		public List<Integer> getUserIds() {
			return userIds;
		}
	}
}
