package com.backend.snippets.service.impl;

import com.backend.snippets.dto.response.TagResponseDto;
import com.backend.snippets.repository.ITagRepository;
import com.backend.snippets.service.ITagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagService implements ITagService {
    private final ITagRepository tagRepository;

    @Override
    public TagResponseDto createTag(String name) {
        return null;
    }

    @Override
    public Optional<TagResponseDto> getTagByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<TagResponseDto> getTagById(Long id) {
        return Optional.empty();
    }

    @Override
    public TagResponseDto updateTag(Long id, String name) {
        return null;
    }

    @Override
    public void deleteTag(Long id) {

    }

    @Override
    public List<TagResponseDto> getAllTags() {
        return List.of();
    }
}
