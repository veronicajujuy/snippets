package com.backend.snippets.repository;

import com.backend.snippets.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILanguageRepository extends JpaRepository<Language, Long> {

}
