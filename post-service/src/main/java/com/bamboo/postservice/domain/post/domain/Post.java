package com.bamboo.postservice.domain.post.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String author;

    private String title;
    @Lob
    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HashTag> hashTagList;
    public void addHashTag(HashTag hashTag) {
        hashTag.setPost(this);
        getHashTagList().add(hashTag);
    }

    @Builder
    public Post(Long postId, String author, String title, String content, LocalDateTime createdAt, List<HashTag> hashTagList) {
        this.postId = postId;
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.hashTagList = hashTagList;
    }
}
