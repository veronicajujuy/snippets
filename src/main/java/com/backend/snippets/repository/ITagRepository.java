package com.backend.snippets.repository;

import com.backend.snippets.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITagRepository extends JpaRepository<Tag, Long> {
}
