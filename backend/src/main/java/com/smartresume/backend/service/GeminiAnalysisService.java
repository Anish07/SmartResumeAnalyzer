package com.smartresume.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class GeminiAnalysisService implements AiAnalysisService {

    private final WebClient webClient;

    @Value("${gemini.model:gemini-1.5-flash}")
    private String model;

    @Value("${gemini.api.key}")
    private String apiKey;

    public GeminiAnalysisService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://generativelanguage.googleapis.com/v1beta/models")
                .build();
    }

    @Override
    @SuppressWarnings("unchecked")
    public String analyzeResume(String resumeText) {
        String prompt = """
                You are an expert AI Resume Analyzer. Analyze the following resume text and provide:
                1. A professional summary (max 3 sentences).
                2. A list of key technical skills.
                3. A job fit score (0-100) for a generic Software Engineer role.
                4. 3 concrete improvement suggestions.

                Resume Text:
                %s
                """.formatted(resumeText);

        // Gemini Request Structure
        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", prompt)))));

        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/" + model + ":generateContent")
                        .queryParam("key", apiKey)
                        .build())
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    try {
                        List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.get("candidates");
                        if (candidates != null && !candidates.isEmpty()) {
                            Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
                            List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
                            if (parts != null && !parts.isEmpty()) {
                                return (String) parts.get(0).get("text");
                            }
                        }
                    } catch (Exception e) {
                        return "Error parsing Gemini response: " + e.getMessage();
                    }
                    return "Error: No response from AI.";
                })
                .onErrorResume(e -> Mono.just("Error calling Gemini: " + e.getMessage()))
                .block();
    }
}
