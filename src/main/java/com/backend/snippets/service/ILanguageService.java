package com.backend.snippets.service;

import com.backend.snippets.dto.modify.LanguageModifyDto;
import com.backend.snippets.dto.modify.TagModifyDto;
import com.backend.snippets.dto.request.LanguageRequestDto;
import com.backend.snippets.dto.request.TagRequestDto;
import com.backend.snippets.dto.response.LanguageResponseDto;
import com.backend.snippets.dto.response.TagResponseDto;

import java.util.List;

public interface ILanguageService {
    LanguageResponseDto createLanguage(LanguageRequestDto languageRequestDto);
    LanguageResponseDto getLanguageByName(String name);
    LanguageResponseDto getLanguageById(Long id);
    LanguageResponseDto updateLanguage(LanguageModifyDto languageModifyDto);
    void deleteLanguage(Long id);
    List<LanguageResponseDto> getAllLanguages();

}
