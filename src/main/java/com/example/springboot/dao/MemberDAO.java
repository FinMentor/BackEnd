package com.example.springboot.dao;

import com.example.springboot.entity.domain.MemberEntity;

import java.util.List;
import java.util.Optional;

public interface MemberDAO {
    MemberEntity save(MemberEntity memberEntity);

    Optional<MemberEntity> findById(String id);

    List<MemberEntity> findById(List<Long> memberIdList);

    Optional<MemberEntity> findById(Long memberId);

    int resetPasswordFailureCount(String id);

    int save(String id, String password);

    List<Object[]> selectListMemberByMainCategoryId(String memberType, Long mainCategoryId);

    List<Long> selectListMentorRankByStar(String memberType, Long mainCategoryId);

    List<Object[]> selectListMentorRankByWeekly(String memberType);

    List<Object[]> selectListMentorRankByMonthly(String memberType);

    List<Object[]> selectListMentorRank(String memberType);

    List<Object[]> selectListMentorCategoryRankByWeekly(String memberType, Long mainCategoryId);

    List<Object[]> selectListMentorCategoryRankByMonthly(String memberType, Long mainCategoryId);
}
