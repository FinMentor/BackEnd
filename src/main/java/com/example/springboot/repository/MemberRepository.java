package com.example.springboot.repository;

import com.example.springboot.dto.MenteeMentorDTO;
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
}
