package com.example.translator.controller;

import com.example.translator.model.TranslationRequest;
import com.example.translator.model.TranslationResponse;
import com.example.translator.service.TranslationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TranslationController {

    private final TranslationService translationService;

    public TranslationController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @PostMapping("/translate")
    public TranslationResponse translate(@RequestBody TranslationRequest request, HttpServletRequest httpRequest) {
        String clientIp = httpRequest.getRemoteAddr();
        return translationService.translate(request, clientIp);
    }
}
