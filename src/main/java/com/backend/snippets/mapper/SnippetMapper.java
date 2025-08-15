package com.backend.snippets.mapper;

import com.backend.snippets.dto.modify.SnippetModifyDto;
import com.backend.snippets.dto.request.SnippetRequestDto;
import com.backend.snippets.dto.response.SnippetResponseDto;
import com.backend.snippets.entity.Snippet;
import com.backend.snippets.mapper.helpers.LanguageHelperMapper;
import com.backend.snippets.mapper.helpers.TagMapperHelper;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = { LanguageHelperMapper.class, TagMapperHelper.class}
)
public interface SnippetMapper {
    // Dto -> Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "languageId", target = "language")
    @Mapping(source = "tagIds", target = "tags")
    @Mapping(target = "createdAt", ignore = true)
    Snippet toEntity (SnippetRequestDto requestDto);
    // Entity -> Dto
    @Mapping(source = "language", target = "languageResponseDto")
    @Mapping(source = "tags", target = "tagResponseDtos")
    SnippetResponseDto toResponseDto(Snippet snippet);

    @Mapping(source = "language", target = "languageResponseDto")
    @Mapping(source = "tags", target = "tagResponseDtos")
    List<SnippetResponseDto> toResponseDtoList(List<Snippet> snippets);

    // Modify Dto -> Entity
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "languageId", target = "language")
    @Mapping(source = "tagIds", target = "tags")
    @Mapping(target = "createdAt", ignore = true)
    void updateEntityFromDto(SnippetModifyDto snippetModifyDto, @MappingTarget Snippet entity);

}
