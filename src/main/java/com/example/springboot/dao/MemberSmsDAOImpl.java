package com.example.springboot.dao;

import com.example.springboot.entity.domain.MemberSmsEntity;
import com.example.springboot.exception.ErrorRequiredValueValidation;
import com.example.springboot.repository.MemberSmsRepository;
import com.example.springboot.util.ExceptionCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberSmsDAOImpl implements MemberSmsDAO {
    private final MemberSmsRepository memberSmsRepository;

    /**
     * 멤버SMS 저장
     * <p>
     * 멤버SMS 테이블에 컬럼 정보를 저장하는 메소드이다.
     *
     * @param memberSmsEntity
     * @return
     */
    @Override
    public MemberSmsEntity save(MemberSmsEntity memberSmsEntity) {
        if (memberSmsEntity.getPhoneFirst() == null || memberSmsEntity.getPhoneFirst().isEmpty()) {
            throw new ErrorRequiredValueValidation(new StringBuilder("phoneFirst는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (memberSmsEntity.getPhoneMiddle() == null || memberSmsEntity.getPhoneMiddle().isEmpty()) {
            throw new ErrorRequiredValueValidation(new StringBuilder("phoneMiddle은 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (memberSmsEntity.getPhoneLast() == null || memberSmsEntity.getPhoneLast().isEmpty()) {
            throw new ErrorRequiredValueValidation(new StringBuilder("phoneLast는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (memberSmsEntity.getMemberEntity() == null || memberSmsEntity.getMemberEntity().getMemberId() == null || memberSmsEntity.getMemberEntity().getMemberId().isEmpty()) {
            throw new ErrorRequiredValueValidation(new StringBuilder("memberId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (memberSmsEntity.getPhoneVerificationCode() == null || memberSmsEntity.getPhoneVerificationCode().isEmpty()) {
            throw new ErrorRequiredValueValidation(new StringBuilder("phoneVerificationCode는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (memberSmsEntity.getPhoneVerifiedStatus() == null) {
            throw new ErrorRequiredValueValidation(new StringBuilder("getPhoneVerifiedStatus는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("save memberSmsEntity: {}", memberSmsEntity);

        return memberSmsRepository.save(memberSmsEntity);
    }
}
