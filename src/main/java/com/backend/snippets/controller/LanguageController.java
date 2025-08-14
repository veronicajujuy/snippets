package com.backend.snippets.controller;

import com.backend.snippets.dto.request.LanguageRequestDto;
import com.backend.snippets.dto.response.LanguageResponseDto;
import com.backend.snippets.service.ILanguageService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/languages")
@RequiredArgsConstructor
public class LanguageController {
    private final ILanguageService languageService;

    @PostMapping
    public ResponseEntity<LanguageResponseDto> createLanguage(@RequestBody @Valid LanguageRequestDto languageRequestDto){
        LanguageResponseDto createdLanguage = languageService.createLanguage(languageRequestDto);
        return ResponseEntity.status(201).body(createdLanguage);
    }

    @GetMapping
    public ResponseEntity<LanguageResponseDto> getLanguageByName(@RequestParam String name){
        LanguageResponseDto language = languageService.getLanguageByName(name);
        return ResponseEntity.ok(language);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LanguageResponseDto> getLanguageById(@PathVariable Long id){
        LanguageResponseDto language = languageService.getLanguageById(id);
        return ResponseEntity.ok(language);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllLanguages(){
        return ResponseEntity.ok(languageService.getAllLanguages());
    }
}
