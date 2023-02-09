package com.bamboo.postservice.presentation.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class
UserRo {
    private Integer userId;

    private String uniqueId;

    private int grade;

    private int room;

    private int number;

    private String name;

    private String profileImage;

    private String email;

    private Role role;

    @Getter
    @AllArgsConstructor
    private enum Role {
        GUEST("ROLE_ADMIN", "어드민"),
        USER("ROLE_USER", "일반 사용자");

        private final String key;
        private final String title;
    }
}
