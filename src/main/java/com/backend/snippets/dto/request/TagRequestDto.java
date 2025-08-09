package com.backend.snippets.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagRequestDto {
    @NotNull(message = "El nombre del tag no puede ser nulo")
    private String name;
}
