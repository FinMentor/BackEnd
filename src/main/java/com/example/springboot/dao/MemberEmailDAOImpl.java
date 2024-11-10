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

    /**
     * 멤버이메일 저장
     * <p>
     * 멤버이메일 테이블에 컬럼 정보를 저장하는 메소드이다.
     *
     * @param memberEmailEntity
     * @return
     */
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
