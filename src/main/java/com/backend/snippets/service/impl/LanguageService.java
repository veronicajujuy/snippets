package com.backend.snippets.service.impl;

import com.backend.snippets.dto.modify.LanguageModifyDto;
import com.backend.snippets.dto.request.LanguageRequestDto;
import com.backend.snippets.dto.response.LanguageResponseDto;
import com.backend.snippets.entity.Language;
import com.backend.snippets.exception.ConflictException;
import com.backend.snippets.exception.NotFoundException;
import com.backend.snippets.mapper.BasicMapper;
import com.backend.snippets.repository.ILanguageRepository;
import com.backend.snippets.service.ILanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LanguageService implements ILanguageService{
    private final ILanguageRepository languageRepository;
    private final BasicMapper mapper;

    @Override
    public LanguageResponseDto createLanguage(LanguageRequestDto languageRequestDto) {
        Language language = mapper.toEntity(languageRequestDto);
        Optional<Language> existingLanguage = languageRepository.findByName(language.getName());
        if(existingLanguage.isPresent()) {
            throw new ConflictException("Language with name " + language.getName() + " already exists.");
        } else {
        Language languageSaved =  languageRepository.save(language);
        return mapper.toResponseDto(languageSaved);
        }
    }

    @Override
    public LanguageResponseDto getLanguageByName(String name) {
        Optional<Language> languageFound = languageRepository.findByName(name);
        if(languageFound.isPresent()) {
            return mapper.toResponseDto(languageFound.get());
        }
        throw new NotFoundException("No existing language with name: " + name);
    }

    @Override
    public LanguageResponseDto getLanguageById(Long id) {
        Optional<Language> languageFound = languageRepository.findById(id);
        if(languageFound.isPresent()) {
            return mapper.toResponseDto(languageFound.get());
        }
        throw new NotFoundException("No existing language with id: " + id);
    }

    @Override
    public LanguageResponseDto updateLanguage(LanguageModifyDto languageModifyDto) {
        Optional<Language> languageFound = languageRepository.findById(languageModifyDto.getId());
        if(languageFound.isPresent()) {
            Language language = languageFound.get();
            language.setName(languageModifyDto.getName());
            language.setIcon(languageModifyDto.getIcon());
            Language updatedLanguage = languageRepository.save(language);
            return mapper.toResponseDto(updatedLanguage);
        }
        throw new NotFoundException("No existing language with id: " + languageModifyDto.getId());
    }

    @Override
    public void deleteLanguage(Long id) {
        Optional<Language> languageFound = languageRepository.findById(id);
        if(languageFound.isPresent()) {
            languageRepository.deleteById(id);
        } else {
            throw new NotFoundException("No existing language with id: " + id);
        }
    }

    @Override
    public List<LanguageResponseDto> getAllLanguages() {
        return languageRepository.findAll()
                .stream()
                .map(mapper::toResponseDto)
                .toList();
    }
}
