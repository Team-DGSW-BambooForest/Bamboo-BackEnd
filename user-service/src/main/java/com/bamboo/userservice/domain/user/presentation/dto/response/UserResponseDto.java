package com.bamboo.userservice.domain.user.presentation.dto.response;

import com.bamboo.userservice.domain.user.UserEntity;
import com.bamboo.userservice.domain.user.domain.type.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor @AllArgsConstructor
public class UserResponseDto {
    private Integer userId;

    private String uniqueId;

    private int grade;

    private int room;

    private int number;

    private String name;

    private String profileImage;

    private String email;

    private Role role;

    public UserResponseDto(UserEntity user) {
        this.userId = user.getUserId();
        this.uniqueId = user.getUniqueId();
        this.grade = user.getGrade();
        this.room = user.getRoom();
        this.number = user.getNumber();
        this.name = user.getName();
        this.profileImage = user.getProfileImage();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
