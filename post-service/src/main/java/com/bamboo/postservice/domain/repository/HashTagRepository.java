package com.bamboo.postservice.domain.repository;

import com.bamboo.postservice.domain.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashTagRepository extends JpaRepository<HashTag, Long> {
}
