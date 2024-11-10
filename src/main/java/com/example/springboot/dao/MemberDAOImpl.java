package com.example.springboot.dao;

import com.example.springboot.entity.domain.MemberEntity;
import com.example.springboot.exception.ErrorRequiredValueValidation;
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

    @Override
    public MemberEntity save(MemberEntity memberEntity) {
        if (memberEntity.getMemberId() == null || memberEntity.getMemberId().isEmpty()) {
            throw new ErrorRequiredValueValidation(new StringBuilder("memberId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }
        if (memberEntity.getPassword() == null || memberEntity.getPassword().isEmpty()) {
            throw new ErrorRequiredValueValidation(new StringBuilder("password는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }
        if (memberEntity.getName() == null || memberEntity.getName().isEmpty()) {
            throw new ErrorRequiredValueValidation(new StringBuilder("name은 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("save memberEntity: {}", memberEntity);

        return memberRepository.save(memberEntity);
    }

    @Override
    public Optional<MemberEntity> findById(String memberId) {
        if (memberId == null || memberId.isEmpty()) {
            throw new ErrorRequiredValueValidation(new StringBuilder("memberId"), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("findById memberId: {}", memberId);

        return memberRepository.findById(memberId);
    }
}
