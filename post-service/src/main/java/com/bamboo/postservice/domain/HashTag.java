package com.bamboo.postservice.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HashTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    private String hashTag;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    public void setPost(Post post) {
        this.post = post;
    }

}
