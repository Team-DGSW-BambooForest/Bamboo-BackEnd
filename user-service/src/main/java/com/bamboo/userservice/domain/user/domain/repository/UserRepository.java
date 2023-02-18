package com.bamboo.userservice.domain.user.domain.repository;

import com.bamboo.userservice.domain.user.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    boolean existsByGradeAndNumberAndRoom(int grade, int number, int room);
}
