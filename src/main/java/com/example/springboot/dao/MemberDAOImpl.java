package com.example.springboot.dao;

import com.example.springboot.entity.domain.MemberEntity;
import com.example.springboot.exception.ErrorRequiredValueValidationException;
import com.example.springboot.repository.MemberRepository;
import com.example.springboot.util.ExceptionCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberDAOImpl implements MemberDAO {
    private final MemberRepository memberRepository;

    /**
     * 멤버 저장
     * <p>
     * 멤버 테이블에 컬럼 정보를 저장하는 메소드이다.
     *
     * @param memberEntity
     * @return
     */
    @Override
    public MemberEntity save(MemberEntity memberEntity) {
        if (memberEntity.getMemberId() == null || memberEntity.getMemberId().isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (memberEntity.getPassword() == null || memberEntity.getPassword().isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("password는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (memberEntity.getName() == null || memberEntity.getName().isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("name은 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (memberEntity.getIntroduce() == null || memberEntity.getIntroduce().isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("introduce는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("save memberEntity: {}", memberEntity);

        return memberRepository.save(memberEntity);
    }

    /**
     * 멤버 조회
     * <p>
     * PK로 멤버 테이블을 조회하는 메소드이다.
     *
     * @param memberId
     * @return
     */
    @Override
    public Optional<MemberEntity> findById(String memberId) {
        if (memberId == null || memberId.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("findById memberId: {}", memberId);

        return memberRepository.findById(memberId);
    }

    /**
     * 아이디와 비밀번호로 멤버 조회
     * <p>
     * 아이디와 비밀번호로 멤버를 조회하는 메소드이다.
     *
     * @param memberId
     * @param password
     * @return
     */
    @Override
    public Optional<MemberEntity> findByMemberIdAndPassword(String memberId, String password) {
        if (memberId == null || memberId.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (password == null || password.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("password는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("findByIdAndPassword memberId: {}, password: {}", memberId, password);

        return memberRepository.findByMemberIdAndPassword(memberId, password);
    }

    /**
     * 비밀번호실패횟수 초기화
     *
     * 멤버아이디로 비밀번호실패횟수를 초기화하는 메소드이다.
     *
     * @param memberId
     * @return
     */
    @Override
    public int resetPasswordFailureCount(String memberId) {
        if (memberId == null || memberId.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("resetPasswordFailureCount memberId: {}", memberId);

        return memberRepository.resetPasswordFailureCount(memberId);
    }

    /**
     * 비밀번호 변경
     * <p>
     * 멤버 테이블의 비밀번호를 변경하는 메소드이다.
     *
     * @param memberId
     * @param password
     * @return
     */
    @Override
    public int save(String memberId, String password) {
        if (memberId == null || memberId.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (password == null || password.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("password는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("save memberId: {}, password: {}", memberId, password);

        return memberRepository.save(memberId, password);
    }
}
