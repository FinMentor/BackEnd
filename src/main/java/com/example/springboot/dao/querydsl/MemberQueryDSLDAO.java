package com.example.springboot.dao.querydsl;

import com.example.springboot.dto.MenteeMentorDTO;

import java.util.List;

public interface MemberQueryDSLDAO {
    List<MenteeMentorDTO> selectListMemberByMainCategoryId(String memberType, Long mainCategoryId);

    List<Long> selectListMentorRankByStar(String memberType, Long mainCategoryId);
}
