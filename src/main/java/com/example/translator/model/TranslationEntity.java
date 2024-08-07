package com.example.translator.model;

import java.time.LocalDateTime;

public class TranslationEntity {
    private Long id;
    private String ipAddress;
    private String inputText;
    private String translatedText;
    private LocalDateTime timestamp;

    public TranslationEntity(String ipAddress, String inputText, String translatedText) {
        this.ipAddress = ipAddress;
        this.inputText = inputText;
        this.translatedText = translatedText;
        this.timestamp = LocalDateTime.now();
    }

