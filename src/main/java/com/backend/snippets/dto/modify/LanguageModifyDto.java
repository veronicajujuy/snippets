package com.backend.snippets.dto.modify;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LanguageModifyDto {
    @NotNull(message = "El id del lenguaje no puede ser nulo")
    private Long id;
    @NotNull(message = "El nombre del lenguaje no puede ser nulo")
    private String name;
    @NotNull(message = "El icono del lenguaje no puede ser nulo")
    private String icon;
}
