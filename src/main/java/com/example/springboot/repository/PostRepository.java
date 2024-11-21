package com.example.springboot.repository;

import com.example.springboot.entity.domain.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    @Modifying
    @Query(value = "UPDATE PostEntity p SET p.viewCount = p.viewCount + 1 WHERE p.postId = :postId")
    void updateViewCount(@Param("postId") Long postId);

    @Query(value = "SELECT p.POST_ID, p.TITLE, p.CONTENT, p.LIKE_COUNT, COUNT(c.COMMENT_ID)" +
            ", CASE " +
            "WHEN TIMESTAMPDIFF(HOUR, p.CREATED_AT, CURRENT_TIMESTAMP()) > 23 THEN DATE_FORMAT(p.CREATED_AT, '%Y.%m.%d') " +
            "WHEN TIMESTAMPDIFF(HOUR, p.CREATED_AT, CURRENT_TIMESTAMP()) = 0 THEN CONCAT(TIMESTAMPDIFF(MINUTE, p.CREATED_AT, CURRENT_TIMESTAMP()) % 60, '분 전') " +
            "ELSE CONCAT(TIMESTAMPDIFF(HOUR, p.CREATED_AT, CURRENT_TIMESTAMP()), '시간 전') " +
            "END " +
            "FROM POST p " +
            "LEFT JOIN COMMENT c ON p.POST_ID = c.POST_ID " +
            "WHERE p.MAIN_CATEGORY_ID = :mainCategoryId " +
            "AND POST_TYPE = :postType " +
            "GROUP BY 1", nativeQuery = true)
    List<Object[]> findAllByMainCategoryId(Long mainCategoryId, String postType);
}
