package com.bamboo.userservice.domain.auth.presentation.dto.api;

import com.bamboo.userservice.domain.user.UserEntity;
import com.bamboo.userservice.domain.user.domain.type.Role;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DodamInfoDto implements Serializable {
    private String uniqueId;
    private int grade;
    private int room;
    private int number;
    private String name;
    private String email;
    private String profileImage;
    private int accessLevel;

    public DodamInfoDto(DodamInfoDto data) {
        this.uniqueId = data.getUniqueId();
        this.grade = data.getGrade();
        this.room = data.getRoom();
        this.number = data.getNumber();
        this.name = data.getName();
        this.email = data.getEmail();
        this.profileImage = data.getProfileImage();
        this.accessLevel = data.getAccessLevel();
    }

    public static UserEntity toEntity(DodamInfoDto data) {
        Role role = Role.USER;
        return UserEntity.builder()
                .uniqueId(data.getUniqueId())
                .name(data.getName())
                .grade(data.getGrade())
                .number(data.getNumber())
                .room(data.getRoom())
                .profileImage(data.getProfileImage())
                .role(role)
                .build();
    }
}
