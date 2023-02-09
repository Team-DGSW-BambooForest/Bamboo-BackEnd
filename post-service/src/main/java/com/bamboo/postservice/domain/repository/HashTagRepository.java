package com.bamboo.postservice.domain.repository;

import com.bamboo.postservice.domain.HashTag;
import com.bamboo.postservice.presentation.dto.reponse.TagRo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HashTagRepository extends JpaRepository<HashTag, Long> {
    List<TagRo> findAllByPost_PostId(Long id);

    @Query(value = "select distinct post_id from hash_tag where hash_tag like %?%", nativeQuery = true)
    List<Long> findDistinctByHashTagContaining (@Param("hash_tag") String hashtag, Pageable pageable);
}
