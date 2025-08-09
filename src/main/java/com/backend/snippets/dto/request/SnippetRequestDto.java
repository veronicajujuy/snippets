package com.backend.snippets.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SnippetRequestDto {
    @NotNull(message = "El título no puede ser nulo")
    private String title;
    private String description;
    @NotNull(message = "El código no puede ser nulo")
    @Size(max = 200000, message = "El código no puede exceder los 200,000 caracteres")
    @Size(min = 50, message = "El código debe tener al menos 50 caracteres")
    private String code;
    @NotNull(message = "El ID del lenguaje no puede ser nulo")
    private Long languageId;
    @NotEmpty(message = "Debe haber al menos un tag asociado")
    private List<Long> tagIds;
}
