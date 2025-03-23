package com.example.demo;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.EmbeddingResponse;

@Service
public class OpenAIEmbeddingsService {

	@Value("${openai.api-key}")
	private String apiKey;

	private final RestTemplate restTemplate;

	public OpenAIEmbeddingsService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public EmbeddingResponse getEmbedding(String input) {
		String url = "https://api.openai.com/v1/embeddings";

		// Set headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(apiKey);

		HashMap<String, Object> requestBody = new HashMap<>();
		requestBody.put("input", input);
		requestBody.put("model", "text-embedding-ada-002");

		HttpEntity<HashMap<String, Object>> request = new HttpEntity<>(requestBody, headers);

		ResponseEntity<EmbeddingResponse> response = restTemplate.postForEntity(url, request, EmbeddingResponse.class);
		return response.getBody();
	}
}
