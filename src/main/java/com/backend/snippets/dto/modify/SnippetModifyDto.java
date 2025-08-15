package com.backend.snippets.dto.modify;

import com.backend.snippets.dto.response.LanguageResponseDto;
import com.backend.snippets.dto.response.TagResponseDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SnippetModifyDto {
    @NotNull(message = "El ID del snippet no puede ser nulo")
    private String id;
    private String title;
    private String description;
    private String code;
    private Long languageId;
    private List<Long> tagIds;
}
