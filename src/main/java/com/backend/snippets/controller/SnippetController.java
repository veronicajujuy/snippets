package com.backend.snippets.controller;

import com.backend.snippets.dto.request.SnippetRequestDto;
import com.backend.snippets.dto.response.SnippetResponseDto;
import com.backend.snippets.service.impl.SnippetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/snippets")
@RequiredArgsConstructor
public class SnippetController {
    private final SnippetService snippetService;

    @PostMapping
    public ResponseEntity<SnippetResponseDto> createSnippet(@Valid @RequestBody SnippetRequestDto snippetRequestDto) {
        SnippetResponseDto createdSnippet = snippetService.createSnippet(snippetRequestDto);
        return ResponseEntity.status(201).body(createdSnippet);
    }

    @GetMapping
    public ResponseEntity<SnippetResponseDto> getSnippetById(@RequestParam String id) {
        SnippetResponseDto snippet = snippetService.getSnippetById(id);
        return ResponseEntity.ok(snippet);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SnippetResponseDto>> getSnippetsByTitle(@RequestParam String searchTerm) {
        List<SnippetResponseDto> snippets = snippetService.getSnippetByTitleOrDescription(searchTerm);
        return ResponseEntity.ok(snippets);
    }

}
