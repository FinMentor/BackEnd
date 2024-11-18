package com.example.springboot.repository;

import com.example.springboot.entity.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    @Modifying
    @Query("UPDATE MemberEntity m SET m.password = :password WHERE m.id = :id")
    int save(String id, String password);

    @Modifying
    @Query("UPDATE MemberEntity m SET m.passwordFailureCount = 0 WHERE m.id = :id")
    int resetPasswordFailureCount(String id);

    Optional<MemberEntity> findById(String id);

    @Query("SELECT m FROM MemberEntity m WHERE m.memberId IN :memberIdList")
    List<MemberEntity> findById(List<Long> memberIdList);

    @Query(value = "SELECT mc.MAIN_CATEGORY_ID, cg1.MEMBER_ID, cg2.MEMBER_ID, cg1.STAR " +
            "FROM MEMBER m " +
            "JOIN CHATROOM_GROUP cg1 ON m.MEMBER_ID = cg1.MEMBER_ID " +
            "JOIN CHATROOM_GROUP cg2 ON cg1.CHATROOM_ID = cg2.CHATROOM_ID " +
            "JOIN MEMBER_CATEGORY mc ON m.MEMBER_ID = mc.MEMBER_ID " +
            "WHERE cg1.MEMBER_ID <> cg2.MEMBER_ID " +
            "AND cg1.STAR IS NOT NULL " +
            "AND m.MEMBER_TYPE = :memberType " +
            "AND mc.MAIN_CATEGORY_ID = :mainCategoryId", nativeQuery = true)
    List<Object[]> selectListMemberByMainCategoryId(String memberType, Long mainCategoryId);

    @Query(value = "SELECT cg1.MEMBER_ID " +
            "FROM MEMBER m " +
            "JOIN CHATROOM_GROUP cg1 ON m.MEMBER_ID = cg1.MEMBER_ID " +
            "JOIN CHATROOM_GROUP cg2 ON cg1.CHATROOM_ID = cg2.CHATROOM_ID " +
            "JOIN MEMBER_CATEGORY mc ON m.MEMBER_ID = mc.MEMBER_ID " +
            "WHERE cg1.MEMBER_ID <> cg2.MEMBER_ID " +
            "AND cg1.STAR IS NOT NULL " +
            "AND m.MEMBER_TYPE = :memberType " +
            "AND mc.MAIN_CATEGORY_ID = :mainCategoryId " +
            "GROUP BY 1 " +
            "ORDER BY SUM(cg1.STAR) DESC", nativeQuery = true)
    List<Long> selectListMentorRankByStar(String memberType, Long mainCategoryId);

    @Query(value = "SELECT m.MEMBER_ID, m.NAME, m.NICKNAME, m.PROFILE_IMAGE_URL, mac.MAIN_CATEGORY_ID, mac.MAIN_CATEGORY_NAME, CAST(TRUNCATE(SUM(cg.STAR) / COUNT(m.MEMBER_ID), 1) AS DOUBLE) " +
            "FROM MEMBER m " +
            "JOIN CHATROOM_GROUP cg ON m.MEMBER_ID = cg.MEMBER_ID " +
            "JOIN MEMBER_CATEGORY mec ON m.MEMBER_ID = mec.MEMBER_ID " +
            "JOIN MAIN_CATEGORY mac ON mec.MAIN_CATEGORY_ID = mac.MAIN_CATEGORY_ID " +
            "WHERE m.MEMBER_TYPE = :memberType " +
            "AND cg.CREATED_AT BETWEEN TIMESTAMP(DATE_SUB(NOW(), INTERVAL WEEKDAY(NOW()) DAY)) AND TIMESTAMP(DATE_ADD(DATE_SUB(NOW(), INTERVAL WEEKDAY(NOW()) DAY), INTERVAL 6 DAY)) " +
            "GROUP BY 1 " +
            "ORDER BY 7 DESC " +
            "LIMIT 10", nativeQuery = true)
    List<Object[]> selectListMentorRankByWeekly(String memberType);

    @Query(value = "SELECT m.MEMBER_ID, m.NAME, m.NICKNAME, m.PROFILE_IMAGE_URL, mac.MAIN_CATEGORY_ID, mac.MAIN_CATEGORY_NAME, CAST(TRUNCATE(SUM(cg.STAR) / COUNT(m.MEMBER_ID), 1) AS DOUBLE) " +
            "FROM MEMBER m " +
            "JOIN CHATROOM_GROUP cg ON m.MEMBER_ID = cg.MEMBER_ID " +
            "JOIN MEMBER_CATEGORY mec ON m.MEMBER_ID = mec.MEMBER_ID " +
            "JOIN MAIN_CATEGORY mac ON mec.MAIN_CATEGORY_ID = mac.MAIN_CATEGORY_ID " +
            "WHERE m.MEMBER_TYPE = :memberType " +
            "AND cg.CREATED_AT BETWEEN TIMESTAMP(DATE_FORMAT(NOW(), '%Y-%m-01')) AND TIMESTAMP(LAST_DAY(NOW())) " +
            "GROUP BY 1 " +
            "ORDER BY 7 DESC " +
            "LIMIT 10", nativeQuery = true)
    List<Object[]> selectListMentorRankByMonthly(String memberType);

    @Query(value = "SELECT m.MEMBER_ID, m.NAME, m.NICKNAME, m.PROFILE_IMAGE_URL, mac.MAIN_CATEGORY_ID, mac.MAIN_CATEGORY_NAME, CAST(TRUNCATE(SUM(cg.STAR) / COUNT(m.MEMBER_ID), 1) AS DOUBLE) " +
            "FROM MEMBER m " +
            "JOIN CHATROOM_GROUP cg ON m.MEMBER_ID = cg.MEMBER_ID " +
            "JOIN MEMBER_CATEGORY mec ON m.MEMBER_ID = mec.MEMBER_ID " +
            "JOIN MAIN_CATEGORY mac ON mec.MAIN_CATEGORY_ID = mac.MAIN_CATEGORY_ID " +
            "WHERE m.MEMBER_TYPE = :memberType " +
            "GROUP BY 1 " +
            "ORDER BY 7 DESC " +
            "LIMIT 3", nativeQuery = true)
    List<Object[]> selectListMentorRank(String memberType);

    @Query(value = "SELECT m.MEMBER_ID, m.NAME, m.NICKNAME, m.PROFILE_IMAGE_URL, mac.MAIN_CATEGORY_ID, mac.MAIN_CATEGORY_NAME, CAST(TRUNCATE(SUM(cg.STAR) / COUNT(m.MEMBER_ID), 1) AS DOUBLE) " +
            "FROM MEMBER m " +
            "JOIN CHATROOM_GROUP cg ON m.MEMBER_ID = cg.MEMBER_ID " +
            "JOIN MEMBER_CATEGORY mec ON m.MEMBER_ID = mec.MEMBER_ID " +
            "JOIN MAIN_CATEGORY mac ON mec.MAIN_CATEGORY_ID = mac.MAIN_CATEGORY_ID " +
            "WHERE m.MEMBER_TYPE = :memberType " +
            "AND cg.CREATED_AT BETWEEN TIMESTAMP(DATE_SUB(NOW(), INTERVAL WEEKDAY(NOW()) DAY)) AND TIMESTAMP(DATE_ADD(DATE_SUB(NOW(), INTERVAL WEEKDAY(NOW()) DAY), INTERVAL 6 DAY)) " +
            "AND mac.MAIN_CATEGORY_ID = :mainCategoryId " +
            "GROUP BY 1 " +
            "ORDER BY 7 DESC " +
            "LIMIT 10", nativeQuery = true)
    List<Object[]> selectListMentorCategoryRankByWeekly(String memberType, Long mainCategoryId);

    @Query(value = "SELECT m.MEMBER_ID, m.NAME, m.NICKNAME, m.PROFILE_IMAGE_URL, mac.MAIN_CATEGORY_ID, mac.MAIN_CATEGORY_NAME, CAST(TRUNCATE(SUM(cg.STAR) / COUNT(m.MEMBER_ID), 1) AS DOUBLE) " +
            "FROM MEMBER m " +
            "JOIN CHATROOM_GROUP cg ON m.MEMBER_ID = cg.MEMBER_ID " +
            "JOIN MEMBER_CATEGORY mec ON m.MEMBER_ID = mec.MEMBER_ID " +
            "JOIN MAIN_CATEGORY mac ON mec.MAIN_CATEGORY_ID = mac.MAIN_CATEGORY_ID " +
            "WHERE m.MEMBER_TYPE = :memberType " +
            "AND cg.CREATED_AT BETWEEN TIMESTAMP(DATE_FORMAT(NOW(), '%Y-%m-01')) AND TIMESTAMP(LAST_DAY(NOW())) " +
            "AND mac.MAIN_CATEGORY_ID = :mainCategoryId " +
            "GROUP BY 1 " +
            "ORDER BY 7 DESC " +
            "LIMIT 10", nativeQuery = true)
    List<Object[]> selectListMentorCategoryRankByMonthly(String memberType, Long mainCategoryId);
}
