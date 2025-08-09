package com.backend.snippets.repository;

import com.backend.snippets.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name);
}
