
package com.example.translator.service;

import com.example.translator.model.*;
import com.example.translator.repository.TranslationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.*;

@Service
public class TranslationService {

    private final RestTemplate restTemplate;
    private final TranslationRepository translationRepository;
    private final ExecutorService executorService;

    @Value("${yandex.api.key}")
    private String apiKey;

    @Value("${yandex.api.url}")
    private String apiUrl;

    @Value("${yandex.folder.id}")
    private String folderId;

    public TranslationService(RestTemplate restTemplate, TranslationRepository translationRepository) {
        this.restTemplate = restTemplate;
        this.translationRepository = translationRepository;
        this.executorService = Executors.newFixedThreadPool(10);
    }

    public TranslationResponse translate(TranslationRequest request, String clientIp) {
        List<Future<String>> futures = new ArrayList<>();
        for (String word : request.getWords()) {
            futures.add(executorService.submit(() -> translateWord(word, request.getTargetLanguage())));
        }

        List<String> translatedWords = futures.stream()
            .map(future -> {
                try {
                    return future.get();
                } catch (InterruptedException | ExecutionException e) {
                    return null;
                }
            })
            .collect(Collectors.toList());

        String translatedText = String.join(" ", translatedWords);

        
        TranslationEntity entity = new TranslationEntity(clientIp, String.join(" ", request.getWords()), translatedText);
        translationRepository.save(entity);

        return new TranslationResponse(translatedText);
    }

    private String translateWord(String word, String targetLanguage) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> body = new HashMap<>();
        body.put("folderId", folderId);
        body.put("targetLanguageCode", targetLanguage);
        body.put("texts", Collections.singletonList(word));

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        ResponseEntity<YandexTranslateResponse> response = restTemplate.postForEntity(apiUrl, request, YandexTranslateResponse.class);

        return response.getBody().getTranslations().get(0).getText();
    }
}
