package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.model.EmbeddingResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeAll;

@SpringBootTest
class OpenAIEmbeddingsServiceTest {

	@BeforeAll
	static void setUp() {
		Dotenv dotenv = Dotenv.load();
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
	}

	@Autowired
	private OpenAIEmbeddingsService openAIEmbeddingsService;

	@MockBean
	private RestTemplate restTemplate; // Mock the RestTemplate bean

	@Test
	void testGetEmbedding() {
		// Arrange
		String input = "Hello world";
		EmbeddingResponse mockResponse = new EmbeddingResponse();
		// Optionally, fill in mockResponse fields here

		when(restTemplate.postForEntity(
				Mockito.anyString(), // or a specific URL if you want
				any(),
				eq(EmbeddingResponse.class)))
				.thenReturn(new ResponseEntity<>(mockResponse, HttpStatus.OK));

		// Act
		EmbeddingResponse result = openAIEmbeddingsService.getEmbedding(input);

		// Assert
		Assertions.assertNotNull(result);
		Assertions.assertEquals(mockResponse, result);

		// Verify RestTemplate was called
		verify(restTemplate).postForEntity(
				Mockito.anyString(),
				any(),
				eq(EmbeddingResponse.class));
	}
}
