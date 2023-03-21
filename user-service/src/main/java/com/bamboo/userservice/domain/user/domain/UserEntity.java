package com.bamboo.userservice.domain.user.domain;

import com.bamboo.userservice.domain.user.domain.type.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(unique = true)
    private String uniqueId;

    private int grade;

    private int room;

    private int number;

    private String name;

    private String profileImage;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;
}