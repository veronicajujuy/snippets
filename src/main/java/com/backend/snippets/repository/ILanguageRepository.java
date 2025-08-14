package com.backend.snippets.repository;

import com.backend.snippets.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ILanguageRepository extends JpaRepository<Language, Long> {
    Optional<Language> findByName(String name);
}
