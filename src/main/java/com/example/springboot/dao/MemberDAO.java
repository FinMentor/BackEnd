package com.example.springboot.dao;

import com.example.springboot.entity.domain.MemberEntity;

import java.util.Optional;

public interface MemberDAO {
    MemberEntity save(MemberEntity memberEntity);

    Optional<MemberEntity> findById(String memberId);

    Optional<MemberEntity> findByMemberIdAndPassword(String memberId, String password);

    int resetPasswordFailureCount(String memberId);

    int save(String memberId, String password);
}
