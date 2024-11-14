package com.example.springboot.dao.querydsl;

import com.example.springboot.entity.domain.MemberEntity;

import java.util.List;

public interface MemberQueryDSLDAO {
    List<MemberEntity> selectListMemberByMemberType(String memberType);
}
