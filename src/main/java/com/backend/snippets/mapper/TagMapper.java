package com.backend.snippets.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.backend.snippets.dto.request.TagRequestDto;
import com.backend.snippets.dto.response.TagResponseDto;
import com.backend.snippets.entity.Tag;

@Mapper(componentModel = "spring")
public interface TagMapper {

    // Convertir de DTO de solicitud a entidad
    Tag toEntity(TagRequestDto requestDto);

    // Convertir de entidad a DTO de respuesta
    TagResponseDto toResponseDto(Tag tag);

    // Actualizar una entidad existente con datos del DTO
    @Mapping(target = "id", ignore = true) // Preserva el ID original
    void updateEntityFromDto(TagRequestDto requestDto, @MappingTarget Tag tag);

    // Mapear una lista de entidades a una lista de DTOs de respuesta
    // MapStruct lo infiere automáticamente
    // List<TagResponseDto> toResponseDtoList(List<Tag> tags);
}
