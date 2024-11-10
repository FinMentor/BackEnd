package com.example.springboot.dao.querydsl;

import com.example.springboot.entity.domain.MemberSmsEntity;
import com.example.springboot.exception.ErrorRequiredValueValidation;
import com.example.springboot.repository.querydsl.MemberSmsQueryDSLRepository;
import com.example.springboot.util.ExceptionCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberSmsQueryDSLDAOImpl implements MemberSmsQueryDSLDAO {
    private final MemberSmsQueryDSLRepository memberSmsQueryDSLRepository;

    @Override
    public List<MemberSmsEntity> selectListMemberByPhone(String phoneFirst, String phoneMiddle) {
        if (phoneFirst == null || phoneFirst.isEmpty()) {
            throw new ErrorRequiredValueValidation(new StringBuilder("phoneFirst는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (phoneMiddle == null || phoneMiddle.isEmpty()) {
            throw new ErrorRequiredValueValidation(new StringBuilder("phoneMiddle은 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("selectListMemberByPhone phoneFirst : {}, phoneMiddle : {}", phoneFirst, phoneMiddle);

        return memberSmsQueryDSLRepository.selectListMemberByPhone(phoneFirst, phoneMiddle);
    }

    @Override
    public List<MemberSmsEntity> selectListMemberSmsByPhoneVerificationCode(String phoneFirst, String phoneMiddle, String phoneVerificationCode) {
        if (phoneFirst == null || phoneFirst.isEmpty()) {
            throw new ErrorRequiredValueValidation(new StringBuilder("phoneFirst는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (phoneMiddle == null || phoneMiddle.isEmpty()) {
            throw new ErrorRequiredValueValidation(new StringBuilder("phoneMiddle은 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (phoneVerificationCode == null || phoneVerificationCode.isEmpty()) {
            throw new ErrorRequiredValueValidation(new StringBuilder("phoneVerificationCode는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("selectListMemberSmsByPhoneVerificationCode phoneFirst : {}, phoneMiddle : {}, phoneVerificationCode : {}", phoneFirst, phoneMiddle, phoneVerificationCode);

        return memberSmsQueryDSLRepository.selectListMemberSmsByPhoneVerificationCode(phoneFirst, phoneMiddle, phoneVerificationCode);
    }
}
