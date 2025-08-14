package com.backend.snippets.service.impl;

import com.backend.snippets.dto.modify.TagModifyDto;
import com.backend.snippets.dto.request.TagRequestDto;
import com.backend.snippets.dto.response.TagResponseDto;
import com.backend.snippets.entity.Tag;
import com.backend.snippets.exception.ConflictException;
import com.backend.snippets.exception.NotFoundException;
import com.backend.snippets.mapper.BasicMapper;
import com.backend.snippets.repository.ITagRepository;
import com.backend.snippets.service.ITagService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService implements ITagService {
    private final ITagRepository tagRepository;
    private final BasicMapper mapper;

    @Override
    public TagResponseDto createTag(TagRequestDto tagRequestDto) {
        if(tagRepository.findByName(tagRequestDto.getName()).isPresent()) {
            throw new ConflictException("Tag with name " + tagRequestDto.getName() + " already exists.");
        }
        Tag tag = tagRepository.save(mapper.toEntity(tagRequestDto));
        return mapper.toResponseDto(tag);
    }

    @Override
    public TagResponseDto getTagByName(String name) {
        return tagRepository.findByName(name)
                .map(mapper::toResponseDto)
                .orElseThrow(() -> new NotFoundException("No existing tag with name: " + name));
    }

    @Override
    public TagResponseDto getTagById(Long id) {
        return tagRepository.findById(id)
                .map(mapper::toResponseDto)
                .orElseThrow(() -> new NotFoundException("No existing tag with id: " + id));
    }

    @Override
    @Transactional
    public TagResponseDto updateTag(TagModifyDto tagModifyDto) {
        Tag tag = tagRepository.findById(tagModifyDto.getId())
                .orElseThrow(() -> new NotFoundException("No existing tag with id: " + tagModifyDto.getId()));

        // Si existe, guardamos los cambios
        tag.setName(tagModifyDto.getName());
        return mapper.toResponseDto(tagRepository.save(tag));
    }

    @Override
    public void deleteTag(Long id) {
        if (!tagRepository.existsById(id)) {
            throw new NotFoundException("No existing tag with id: " + id);
        }
        tagRepository.deleteById(id);
    }

    @Override
    public List<TagResponseDto> getAllTags() {
        return mapper.toTagDtoList(tagRepository.findAll());
    }
}
