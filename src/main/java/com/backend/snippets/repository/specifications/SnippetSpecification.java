package com.backend.snippets.repository.specifications;

import com.backend.snippets.entity.Snippet;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Set;

public class SnippetSpecification {
    public static Specification<Snippet> hasTitleOrDescription(String search) {
        return (root, query, criteriaBuilder) ->
                search == null || search.isBlank() ? null : criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + search.toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + search.toLowerCase() + "%")
                );

    }
    public static Specification<Snippet> hasLanguageId(Long languageId) {
        return (root, query, criteriaBuilder) ->
                languageId == null ? null : criteriaBuilder.equal(
                        root.get("language").get("id"), languageId);
    }

    public static Specification<Snippet> hasTagIds(List<Long> tagIds) {
        return (root, query, criteriaBuilder) ->
                tagIds == null || tagIds.isEmpty() ? null : root.join("tags").get("id").in(tagIds);
    }

}
