package com.example.springboot.repository;

import com.example.springboot.entity.MemberSmsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberSmsRepository extends JpaRepository<MemberSmsEntity, Integer> {
    List<MemberSmsEntity> findByPhoneFirstAndPhoneMiddleAndPhoneVerificationCode(String phoneFirst, String phoneMiddle, String phoneVerificationCode);
}
