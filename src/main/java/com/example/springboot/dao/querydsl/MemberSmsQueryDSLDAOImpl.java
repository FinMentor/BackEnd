package com.example.springboot.dao.querydsl;

import com.example.springboot.entity.domain.MemberSmsEntity;
import com.example.springboot.exception.ErrorRequiredValueValidationException;
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

    /**
     * 휴대폰으로 멤버리스트 조회
     * <p>
     * 휴대폰 정보로 멤버리스트를 조회하는 메소드이다.
     *
     * @param phoneFirst
     * @param phoneMiddle
     * @return
     */
    @Override
    public List<MemberSmsEntity> selectListMemberByPhone(String phoneFirst, String phoneMiddle) {
        if (phoneFirst == null || phoneFirst.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("phoneFirst는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (phoneMiddle == null || phoneMiddle.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("phoneMiddle은 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("selectListMemberByPhone phoneFirst : {}, phoneMiddle : {}", phoneFirst, phoneMiddle);

        return memberSmsQueryDSLRepository.selectListMemberByPhone(phoneFirst, phoneMiddle);
    }

    /**
     * 휴대폰인증코드로 멤버SMS리스트 조회
     * <p>
     * 휴대폰과 휴대폰인증코드로 멤버SMS리스트를 조회하는 메소드이다.
     *
     * @param phoneFirst
     * @param phoneMiddle
     * @param phoneVerificationCode
     * @return
     */
    @Override
    public List<MemberSmsEntity> selectListMemberSmsByPhoneVerificationCode(String phoneFirst, String phoneMiddle, String phoneVerificationCode) {
        if (phoneFirst == null || phoneFirst.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("phoneFirst는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (phoneMiddle == null || phoneMiddle.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("phoneMiddle은 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (phoneVerificationCode == null || phoneVerificationCode.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("phoneVerificationCode는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("selectListMemberSmsByPhoneVerificationCode phoneFirst : {}, phoneMiddle : {}, phoneVerificationCode : {}", phoneFirst, phoneMiddle, phoneVerificationCode);

        return memberSmsQueryDSLRepository.selectListMemberSmsByPhoneVerificationCode(phoneFirst, phoneMiddle, phoneVerificationCode);
    }

    /**
     * 아이디, 이름, 휴대폰으로 멤버리스트 조회
     * <p>
     * 아이디와 이름, 휴대폰으로 멤버리스트를 조회하는 메소드이다.
     *
     * @param memberId
     * @param name
     * @param phoneFirst
     * @param phoneMiddle
     * @return
     */
    @Override
    public List<MemberSmsEntity> selectListMemberByIdAndNameAndPhone(String memberId, String name, String phoneFirst, String phoneMiddle) {
        if (memberId == null || memberId.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (name == null || name.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("name은 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (phoneFirst == null || phoneFirst.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("phoneFirst는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (phoneMiddle == null || phoneMiddle.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("phoneMiddle은 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("selectListMemberByIdAndNameAndPhone memberId : {}, name : {}, phoneFirst : {}, phoneMiddle : {}", memberId, name, phoneFirst, phoneMiddle);

        return memberSmsQueryDSLRepository.selectListMemberByIdAndNameAndPhone(memberId, name, phoneFirst, phoneMiddle);
    }
}
