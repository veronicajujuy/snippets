package com.backend.snippets.mapper.helpers;

import com.backend.snippets.entity.Language;
import com.backend.snippets.exception.NotFoundException;
import com.backend.snippets.repository.ILanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LanguageHelperMapper {
    private final ILanguageRepository languageRepository;

    public Language fromId(Long id){
        return languageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existing language with id: " + id));
    }
}
