package com.example.velogcrud.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String authorName;

    @Builder
    public Post(String title, String content, String authorName) {
        this.title = title;
        this.content = content;
        this.authorName = authorName;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}