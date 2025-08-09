package com.backend.snippets.mapper;

import com.backend.snippets.dto.modify.LanguageModifyDto;
import com.backend.snippets.dto.modify.TagModifyDto;
import com.backend.snippets.dto.request.LanguageRequestDto;
import com.backend.snippets.dto.response.LanguageResponseDto;
import com.backend.snippets.entity.Language;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.backend.snippets.dto.request.TagRequestDto;
import com.backend.snippets.dto.response.TagResponseDto;
import com.backend.snippets.entity.Tag;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BasicMapper {
    // Mapping for Tag entity and DTOs

    Tag toEntity(TagRequestDto requestDto);

    TagResponseDto toResponseDto(Tag tag);

    Tag toEntity(TagModifyDto tagModifyDto);

    List<TagResponseDto> toTagDtoList(List<Tag> tags);

    // Mapping for Language entity and DTOs
    Language toEntity(LanguageRequestDto languageRequestDto);

    LanguageResponseDto toResponseDto(Language language);
    Language toEntity(LanguageModifyDto languageModifyDto);

    List<LanguageResponseDto> toLanguageDtoList(List<Language> languages);
}
