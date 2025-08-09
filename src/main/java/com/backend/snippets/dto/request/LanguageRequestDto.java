package com.backend.snippets.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LanguageRequestDto {
    @NotNull(message = "El nombre del lenguaje no puede ser nulo")
    private String name;
}
