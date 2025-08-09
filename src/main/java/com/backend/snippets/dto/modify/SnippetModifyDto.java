package com.backend.snippets.dto.modify;

import com.backend.snippets.dto.response.LanguageResponseDto;
import com.backend.snippets.dto.response.TagResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SnippetModifyDto {
    private Long id;
    private String title;
    private String description;
    private String code;
    private LanguageModifyDto languageModifyDto;
    private Set<TagModifyDto> tagModifyDtos;
}
