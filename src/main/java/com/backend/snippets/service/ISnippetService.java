package com.backend.snippets.service;

import com.backend.snippets.dto.modify.SnippetModifyDto;
import com.backend.snippets.dto.request.SnippetRequestDto;
import com.backend.snippets.dto.response.SnippetResponseDto;

import java.util.List;

public interface ISnippetService {
    SnippetResponseDto createSnippet(SnippetRequestDto snippetRequestDto);
    List<SnippetResponseDto> getSnippetByTitleOrDescription(String searchTerm);
    SnippetResponseDto getSnippetById(String id);
    SnippetResponseDto updateTag(SnippetModifyDto snippetModifyDto);
    void deleteSnippet(String id);
    List<SnippetResponseDto> getAllSnippets();
    List<SnippetResponseDto> getSnippetsByFilters(String search, Long languageId, List<Long> tagIds, String sortBy);
    List<SnippetResponseDto> findByFilters(String search, Long languageId, List<Long> tagIds);
}
