package com.example.springboot.repository;

import com.example.springboot.entity.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MemberRepository extends JpaRepository<MemberEntity, String> {
    @Modifying
    @Query("UPDATE MemberEntity m SET m.password = :password WHERE m.memberId = :memberId")
    int save(String memberId, String password);
}
