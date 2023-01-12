package com.bamboo.postservice.domain.repository;


import com.bamboo.postservice.domain.HashTag;
import com.bamboo.postservice.presentation.dto.reponse.TagRo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HashTagRepository extends JpaRepository<HashTag, Long> {
    List<TagRo> findByPost_PostId(Long id);


    List<HashTag> findByHashTagContaining (String hashtag);
}
