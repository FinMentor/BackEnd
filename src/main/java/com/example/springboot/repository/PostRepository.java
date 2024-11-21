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
            "GROUP BY 1 " +
            "ORDER BY p.CREATED_AT DESC", nativeQuery = true)
    List<Object[]> findAllByMainCategoryId(Long mainCategoryId, String postType);

    @Query(value = "SELECT p.POST_ID, p.TITLE, p.CONTENT, p.LIKE_COUNT, COUNT(c.COMMENT_ID)" +
            ", CASE " +
            "WHEN TIMESTAMPDIFF(HOUR, p.CREATED_AT, CURRENT_TIMESTAMP()) > 23 THEN DATE_FORMAT(p.CREATED_AT, '%Y.%m.%d') " +
            "WHEN TIMESTAMPDIFF(HOUR, p.CREATED_AT, CURRENT_TIMESTAMP()) = 0 THEN CONCAT(TIMESTAMPDIFF(MINUTE, p.CREATED_AT, CURRENT_TIMESTAMP()) % 60, '분 전') " +
            "ELSE CONCAT(TIMESTAMPDIFF(HOUR, p.CREATED_AT, CURRENT_TIMESTAMP()), '시간 전') " +
            "END " +
            "FROM POST p " +
            "LEFT JOIN COMMENT c ON p.POST_ID = c.POST_ID " +
            "WHERE POST_TYPE = :postType " +
            "GROUP BY 1 " +
            "ORDER BY p.CREATED_AT DESC", nativeQuery = true)
    List<Object[]> findAll(String postType);

    @Query(value = "SELECT p FROM PostEntity p JOIN FETCH p.memberEntity m WHERE p.postType = :postType ORDER BY p.createdAt DESC")
    List<PostEntity> findAllMentor(String postType);

    @Query(value = "SELECT p FROM PostEntity p JOIN FETCH p.memberEntity m WHERE p.mainCategoryId = :mainCategoryId AND p.postType = :postType ORDER BY p.createdAt DESC")
    List<PostEntity> findAllMentorByMainCategoryId(Long mainCategoryId, String postType);

    @Query(value = "SELECT p.POST_ID" +
            ", m1.NICKNAME" +
            ", m1.PROFILE_IMAGE_URL" +
            ", p.TITLE" +
            ", p.CONTENT" +
            ", p.VIEW_COUNT" +
            ", p.LIKE_COUNT" +
            ", (SELECT COUNT(*) FROM COMMENT WHERE p.POST_ID = POST_ID)" +
            ", CASE " +
            "WHEN TIMESTAMPDIFF(HOUR, p.CREATED_AT, CURRENT_TIMESTAMP()) > 23 THEN DATE_FORMAT(p.CREATED_AT, '%Y.%m.%d') " +
            "WHEN TIMESTAMPDIFF(HOUR, p.CREATED_AT, CURRENT_TIMESTAMP()) = 0 THEN CONCAT(TIMESTAMPDIFF(MINUTE, p.CREATED_AT, CURRENT_TIMESTAMP()) % 60, '분 전') " +
            "ELSE CONCAT(TIMESTAMPDIFF(HOUR, p.CREATED_AT, CURRENT_TIMESTAMP()), '시간 전') " +
            "END" +
            ", c.COMMENT_ID" +
            ", mc.MAIN_CATEGORY_NAME" +
            ", m2.NICKNAME" +
            ", m2.PROFILE_IMAGE_URL" +
            ", c.CONTENT" +
            ", CASE " +
            "WHEN TIMESTAMPDIFF(HOUR, c.CREATED_AT, CURRENT_TIMESTAMP()) > 23 THEN DATE_FORMAT(c.CREATED_AT, '%Y.%m.%d') " +
            "WHEN TIMESTAMPDIFF(HOUR, c.CREATED_AT, CURRENT_TIMESTAMP()) = 0 THEN CONCAT(TIMESTAMPDIFF(MINUTE, c.CREATED_AT, CURRENT_TIMESTAMP()) % 60, '분 전') " +
            "ELSE CONCAT(TIMESTAMPDIFF(HOUR, c.CREATED_AT, CURRENT_TIMESTAMP()), '시간 전') " +
            "END " +
            "FROM POST p " +
            "JOIN MEMBER m1 ON p.MEMBER_ID = m1.MEMBER_ID " +
            "LEFT JOIN COMMENT c ON p.POST_ID = c.POST_ID " +
            "LEFT JOIN MEMBER m2 ON c.MEMBER_ID = m2.MEMBER_ID " +
            "LEFT JOIN MEMBER_CATEGORY mec ON m2.MEMBER_ID = mec.MEMBER_ID " +
            "LEFT JOIN MAIN_CATEGORY mc ON mec.MAIN_CATEGORY_ID = mc.MAIN_CATEGORY_ID " +
            "WHERE p.POST_ID = :postId " +
            "AND p.POST_TYPE = :postType", nativeQuery = true)
    List<Object[]> postDetails(Long postId, String postType);
}
