package com.example.springboot.repository;

import com.example.springboot.entity.domain.PostEntity;
import org.hibernate.sql.Insert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE PostEntity p SET p.viewCount = p.viewCount + 1 WHERE p.postId = :postId")
    void updateViewCount(@Param("postId") Long postId);
}
