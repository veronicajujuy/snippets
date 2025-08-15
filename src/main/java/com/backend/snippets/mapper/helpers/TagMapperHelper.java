package com.backend.snippets.mapper.helpers;

import com.backend.snippets.entity.Tag;
import com.backend.snippets.repository.ITagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class TagMapperHelper {
    private final ITagRepository tagRepository;

    public Set<Tag> fromIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) return Set.of();
        List<Tag> tags = tagRepository.findAllById(ids);
        if (tags.size() != ids.size()) {
            throw new IllegalArgumentException("Some tags with the provided IDs do not exist.");
        }
        return Set.copyOf(tags);
    }
}
