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
}
