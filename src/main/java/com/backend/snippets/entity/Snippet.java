package com.backend.snippets.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "snippets")
public class Snippet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String title;
    @Lob
    @Column(columnDefinition = "MEDIUMTEXT", nullable = false)
    private String code;
    private String description;
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    @ManyToOne // A snippet belongs to one language
    private Language language;

    @ManyToMany // A snippet can have multiple tags
    @JoinTable(
        name = "snippet_tags",
        joinColumns = @JoinColumn(name = "snippet_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();
}
