package com.backend.snippets.repository;

import com.backend.snippets.entity.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public interface ISnippetRepository extends JpaRepository<Snippet, String> {
    List<Snippet> findByTitleOrDescriptionContainingIgnoreCase(String title, String description);
    Optional<Snippet> findByTitle(String title);
}
