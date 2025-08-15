package com.backend.snippets.service.impl;

import com.backend.snippets.dto.modify.SnippetModifyDto;
import com.backend.snippets.dto.request.SnippetRequestDto;
import com.backend.snippets.dto.response.SnippetResponseDto;
import com.backend.snippets.entity.Snippet;
import com.backend.snippets.exception.ConflictException;
import com.backend.snippets.exception.NotFoundException;
import com.backend.snippets.mapper.SnippetMapper;
import com.backend.snippets.repository.ISnippetRepository;
import com.backend.snippets.service.ISnippetService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SnippetService implements ISnippetService {
    private final ISnippetRepository snippetRepository;
    private final SnippetMapper snippetMapper;

    @Override
    public SnippetResponseDto createSnippet(SnippetRequestDto snippetRequestDto) {
        Snippet snippet = snippetMapper.toEntity(snippetRequestDto);
        Optional<Snippet> existingSnippet = snippetRepository.findByTitle(snippet.getTitle());
        if (existingSnippet.isPresent()) {
            throw new ConflictException("Snippet with title " + snippet.getTitle() + " already exists.");
        }
        Snippet savedSnippet = snippetRepository.save(snippet);
        return snippetMapper.toResponseDto(savedSnippet);
    }

    @Override
    public List<SnippetResponseDto> getSnippetByTitleOrDescription(String searchTerm) {
        List<Snippet> snippets = snippetRepository.findByTitleOrDescriptionContainingIgnoreCase(searchTerm, searchTerm);
        if (snippets.isEmpty()) {
            throw new ConflictException("No snippets found with the provided title or description.");
        }
        return snippets.stream()
                .map(snippetMapper::toResponseDto)
                .toList();
    }

    @Override
    public SnippetResponseDto getSnippetById(String id) {
        return snippetRepository.findById(id)
                .map(snippetMapper::toResponseDto)
                .orElseThrow(() -> new NotFoundException("No existing snippet with id: " + id));
    }

    @Override
    @Transactional
    public SnippetResponseDto updateTag(SnippetModifyDto snippetModifyDto) {
       Snippet snippet = snippetRepository.findById(snippetModifyDto.getId())
                .orElseThrow(() -> new NotFoundException("No existing snippet with id: " + snippetModifyDto.getId()));

        snippetMapper.updateEntityFromDto(snippetModifyDto, snippet);
        if (snippetModifyDto.getTagIds() != null && snippetModifyDto.getTagIds().isEmpty()) {
            snippet.setTags(Collections.emptySet());
        }

        snippet.setCreatedAt(LocalDateTime.now());
        Snippet updated = snippetRepository.save(snippet);
        return snippetMapper.toResponseDto(updated);

    }

    @Override
    public void deleteSnippet(String id) {
        Optional<Snippet> snippetFound = snippetRepository.findById(id);
        if (snippetFound.isPresent()) {
            snippetRepository.deleteById(id);
        } else {
            throw new NotFoundException("No existing snippet with id: " + id);
        }
    }

    @Override
    public List<SnippetResponseDto> getAllSnippets() {
        List<Snippet> snippets = snippetRepository.findAll();
        return snippets.stream()
                .map(snippetMapper::toResponseDto)
                .collect(Collectors.toList());
    }

}
