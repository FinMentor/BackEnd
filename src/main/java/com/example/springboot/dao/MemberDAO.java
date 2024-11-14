package com.example.springboot.dao;

import com.example.springboot.entity.domain.MemberEntity;

import java.util.List;
import java.util.Optional;

public interface MemberDAO {
    MemberEntity save(MemberEntity memberEntity);

    Optional<MemberEntity> findById(String id);

    List<MemberEntity> findById(List<Long> memberIdList);

    int resetPasswordFailureCount(String id);

    int save(String id, String password);
}
