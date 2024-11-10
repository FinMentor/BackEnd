package com.example.springboot.dao.querydsl;

import com.example.springboot.entity.domain.MemberSmsEntity;

import java.util.List;

public interface MemberSmsQueryDSLDAO {
    List<MemberSmsEntity> selectListMemberByPhone(String phoneFirst, String phoneMiddle);

    List<MemberSmsEntity> selectListMemberSmsByPhoneVerificationCode(String phoneFirst, String phoneMiddle, String phoneVerificationCode);
}
