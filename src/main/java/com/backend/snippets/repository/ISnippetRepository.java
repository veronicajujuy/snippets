package com.backend.snippets.repository;

import com.backend.snippets.entity.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public interface ISnippetRepository extends JpaRepository<Snippet, String>,JpaSpecificationExecutor<Snippet> {
    List<Snippet> findByTitleOrDescriptionContainingIgnoreCase(String title, String description);
    Optional<Snippet> findByTitle(String title);
}
