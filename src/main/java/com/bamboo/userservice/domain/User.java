package com.bamboo.userservice.domain;

import com.bamboo.userservice.domain.type.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(unique = true)
    private String uniqueId;

    private int grade;

    private int number;

    private String name;

    private String profileImage;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String uniqueId, int grade, int number, String name, String profileImage, String email, Role role) {
        this.uniqueId = uniqueId;
        this.grade = grade;
        this.number = number;
        this.name = name;
        this.profileImage = profileImage;
        this.email = email;
        this.role = role;
    }
}
