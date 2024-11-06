package com.example.springboot.repository;

import com.example.springboot.dto.SmsDTO;
import com.example.springboot.entity.MemberSmsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberSmsRepository extends JpaRepository<MemberSmsEntity, Integer> {
    List<MemberSmsEntity> select(SmsDTO smsDTO);

    int insert(SmsDTO smsDTO);

    int updatePhoneVerification(SmsDTO smsDTO);

    int updatePhoneVerificationStatus(SmsDTO smsDTO);
}
