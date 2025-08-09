package com.backend.snippets.dto.modify;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagModifyDto {
    @NotNull(message = "El id del tag no puede ser nulo")
    private Long id;
    @NotBlank(message = "El nombre del tag no puede estar vacío")
    @NotNull(message = "El nombre del tag no puede ser nulo")
    @Size(min = 2, max = 50, message = "El nombre del tag debe tener entre 2 y 50 caracteres")
    private String name;

}
