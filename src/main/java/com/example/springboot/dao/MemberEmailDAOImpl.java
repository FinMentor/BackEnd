package com.example.springboot.dao;

import com.example.springboot.entity.domain.MemberEmailEntity;
import com.example.springboot.exception.ErrorRequiredValueValidation;
import com.example.springboot.repository.MemberEmailRepository;
import com.example.springboot.util.ExceptionCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberEmailDAOImpl implements MemberEmailDAO {
    private final MemberEmailRepository memberEmailRepository;

    @Override
    public MemberEmailEntity save(MemberEmailEntity memberEmailEntity) {
        if (memberEmailEntity.getEmail() == null || memberEmailEntity.getEmail().isEmpty()) {
            throw new ErrorRequiredValueValidation(new StringBuilder("email은 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (memberEmailEntity.getMemberEntity() == null || memberEmailEntity.getMemberEntity().getMemberId() == null || memberEmailEntity.getMemberEntity().getMemberId().isEmpty()) {
            throw new ErrorRequiredValueValidation(new StringBuilder("memberId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (memberEmailEntity.getEmailVerificationCode() == null || memberEmailEntity.getEmailVerificationCode().isEmpty()) {
            throw new ErrorRequiredValueValidation(new StringBuilder("emailVerificationCode는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (memberEmailEntity.getEmailVerifiedStatus() == null) {
            throw new ErrorRequiredValueValidation(new StringBuilder("emailVerifiedStatus는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("save memberEmailEntity: {}", memberEmailEntity);

        return memberEmailRepository.save(memberEmailEntity);
    }
}
