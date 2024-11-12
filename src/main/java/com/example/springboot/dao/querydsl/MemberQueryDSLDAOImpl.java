package com.example.springboot.dao.querydsl;

import com.example.springboot.entity.domain.MemberEntity;
import com.example.springboot.exception.ErrorRequiredValueValidation;
import com.example.springboot.repository.querydsl.MemberQueryDSLRepository;
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
public class MemberQueryDSLDAOImpl implements MemberQueryDSLDAO {
    private final MemberQueryDSLRepository memberCategoryQueryDSLRepository;

    @Override
    public List<MemberEntity> selectListMemberByMemberType(String memberType) {
        if (memberType == null || memberType.isEmpty()) {
            throw new ErrorRequiredValueValidation(new StringBuilder("memberType은 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("selectListMemberByMainCategoryId memberType : {}", memberType);

        return memberCategoryQueryDSLRepository.selectListMemberByMemberType(memberType);
    }
}
