package com.example.springboot.repository;

import com.example.springboot.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {
    Optional<MemberEntity> findByMemberId(String memberId);

    @Query(value = "SELECT m.* " +
            "FROM MEMBER m " +
            "JOIN MEMBER_SMS ms ON m.MEMBER_ID = ms.MEMBER_ID " +
            "WHERE ms.PHONE_FIRST = :phoneFirst " +
            "AND ms.PHONE_MIDDLE = :phoneMiddle",
            nativeQuery = true)
    List<MemberEntity> findMemberByPhone(String phoneFirst, String phoneMiddle);
}
