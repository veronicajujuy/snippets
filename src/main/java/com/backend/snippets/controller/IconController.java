package com.backend.snippets.controller;

import com.backend.snippets.exception.BadRequestException;
import com.backend.snippets.service.impl.IconService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/icons")
@RequiredArgsConstructor
public class IconController {
    private final IconService iconService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadIcon(@RequestParam("file") MultipartFile file) {
        String publicUrl = iconService.saveIcon512(file);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("url", publicUrl));
    }
}
