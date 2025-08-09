package com.backend.snippets.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SnippetResponseDto {
    private Long id;
    private String title;
    private String description;
    private String code;
    private LanguageResponseDto languageResponseDto;
    private Set<TagResponseDto> tagResponseDtoSet;
}
