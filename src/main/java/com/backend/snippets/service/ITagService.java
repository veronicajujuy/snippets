package com.backend.snippets.service;

import com.backend.snippets.dto.response.TagResponseDto;

import java.util.List;
import java.util.Optional;

public interface ITagService {
    TagResponseDto createTag(String name);
    Optional<TagResponseDto> getTagByName(String name);
    Optional<TagResponseDto> getTagById(Long id);
    TagResponseDto updateTag(Long id, String name);
    void deleteTag(Long id);
    List<TagResponseDto> getAllTags();
}
