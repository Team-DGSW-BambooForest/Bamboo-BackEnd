package com.bamboo.userservice.domain.user;

import com.bamboo.userservice.domain.user.domain.type.Role;
import com.bamboo.userservice.global.exception.GlobalException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
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

    @Builder
    public UserEntity(String uniqueId, int grade, int number, String name, String profileImage, String email, Role role, int room) {
        this.uniqueId = uniqueId;
        this.grade = grade;
        this.number = number;
        this.room = room;
        this.name = name;
        this.profileImage = profileImage;
        this.email = email;
        this.role = role;
    }

    public static class NotFoundException extends GlobalException {
        public NotFoundException() {
            super(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다.");
        }
    }

    public static class UnAuthenticationException extends GlobalException {
        public UnAuthenticationException() {
            super(HttpStatus.UNAUTHORIZED, "토큰이 입력되지 않았습니다.");
        }
    }
}