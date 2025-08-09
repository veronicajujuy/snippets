package com.backend.snippets.service;

import com.backend.snippets.dto.modify.TagModifyDto;
import com.backend.snippets.dto.request.TagRequestDto;
import com.backend.snippets.dto.response.TagResponseDto;

import java.util.List;

public interface ITagService {
    TagResponseDto createTag(TagRequestDto tagRequestDto);
    TagResponseDto getTagByName(String name);
    TagResponseDto getTagById(Long id);
    TagResponseDto updateTag(TagModifyDto tagModifyDto);
    void deleteTag(Long id);
    List<TagResponseDto> getAllTags();
}
