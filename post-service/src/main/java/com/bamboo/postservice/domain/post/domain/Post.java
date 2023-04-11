package com.bamboo.postservice.domain.post.domain;

import com.bamboo.postservice.domain.post.domain.enums.PostStatus;
import com.bamboo.postservice.domain.post.domain.enums.PostType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Lob
    private String content;

    private String profileImage;

    private String author;

    @Enumerated(EnumType.STRING)
    private PostStatus status;
    public void setStatus(PostStatus status) {
        this.status = status;
    }

    @Enumerated(EnumType.STRING)
    private PostType type;

    @CreationTimestamp
    private LocalDateTime createdAt;


    @Builder
    public Post(Long postId, String content, String profileImage, String author, PostStatus status, LocalDateTime createdAt, PostType type) {
        this.postId = postId;
        this.content = content;
        this.profileImage = profileImage;
        this.author = author;
        this.status = status;
        this.createdAt = createdAt;
        this.type = type;
    }
}
