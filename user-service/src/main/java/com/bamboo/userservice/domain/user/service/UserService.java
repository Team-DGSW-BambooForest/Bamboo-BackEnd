package com.bamboo.userservice.domain.user.service;

import com.bamboo.userservice.domain.auth.presentation.dto.api.DOpenApiDto;
import com.bamboo.userservice.domain.auth.presentation.dto.api.DodamInfoDto;
import com.bamboo.userservice.domain.user.UserEntity;
import com.bamboo.userservice.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    @Transactional
    public UserEntity save(DOpenApiDto dOpenApiDto) {
        DodamInfoDto data = dOpenApiDto.getDodamInfoData();
        UserEntity user = DodamInfoDto.toEntity(data);
        if(isExisted(user)){
            return user;
        }
        return userRepository.save(user);
    }

    @Transactional
    protected boolean isExisted(UserEntity user) {
        return userRepository.existsByGradeAndNumberAndRoom(user.getGrade(), user.getNumber(), user.getRoom());
    }
}
