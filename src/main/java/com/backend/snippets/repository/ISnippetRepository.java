package com.backend.snippets.repository;

import com.backend.snippets.entity.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISnippetRepository extends JpaRepository<Snippet, String> {
}
