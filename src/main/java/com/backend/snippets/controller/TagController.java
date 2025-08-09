package com.backend.snippets.controller;

import com.backend.snippets.dto.modify.TagModifyDto;
import com.backend.snippets.dto.request.TagRequestDto;
import com.backend.snippets.dto.response.TagResponseDto;
import com.backend.snippets.service.ITagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {
    private final ITagService tagService;

    @PostMapping
    public ResponseEntity<TagResponseDto> createTag(@Valid @RequestBody TagRequestDto tagRequestDto) {
        TagResponseDto createdTag = tagService.createTag(tagRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTag);
    }

    @GetMapping()
    public ResponseEntity<TagResponseDto> findTagByName(@RequestParam String name) {
        TagResponseDto tag = tagService.getTagByName(name);
        return ResponseEntity.ok(tag);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagResponseDto> findTagById(@PathVariable Long id) {
        TagResponseDto tag = tagService.getTagById(id);
        return ResponseEntity.ok(tag);
    }

    @PutMapping
    public ResponseEntity<TagResponseDto> updateTag(@Valid @RequestBody TagModifyDto tagModifyDto) {
        TagResponseDto updatedTag = tagService.updateTag(tagModifyDto);
        return ResponseEntity.ok(updatedTag);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<TagResponseDto>> getAllTags() {
        List<TagResponseDto> tags = tagService.getAllTags();
        return ResponseEntity.ok(tags);
    }
}
