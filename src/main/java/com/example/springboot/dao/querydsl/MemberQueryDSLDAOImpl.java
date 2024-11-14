package com.example.springboot.dao.querydsl;

import com.example.springboot.dto.MenteeMentorDTO;
import com.example.springboot.exception.ErrorRequiredValueValidationException;
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

    /**
     * 멤버리스트 조회
     * <p>
     * 멤버유형 및 메인카테고리아이디로 멤버리스트를 조회하는 메소드이다.
     *
     * @param memberType
     * @param mainCategoryId
     * @return
     */
    @Override
    public List<MenteeMentorDTO> selectListMemberByMainCategoryId(String memberType, Long mainCategoryId) {
        if (memberType == null || memberType.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberType은 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (mainCategoryId == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("mainCategoryId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("selectListMemberByMainCategoryId memberType : {}, mainCategoryId : {}", memberType, mainCategoryId);

        return memberCategoryQueryDSLRepository.selectListMemberByMemberType(memberType, mainCategoryId);
    }

    /**
     * 멘토랭킹리스트 조회
     * <p>
     * 별점으로 멘토랭킹리스트를 조회한다.
     *
     * @param memberType
     * @param mainCategoryId
     * @return
     */
    @Override
    public List<Long> selectListMentorRankByStar(String memberType, Long mainCategoryId) {
        if (memberType == null || memberType.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberType은 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (mainCategoryId == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("mainCategoryId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("selectListMentorRankByStar memberType : {}, mainCategoryId : {}", memberType, mainCategoryId);

        return memberCategoryQueryDSLRepository.selectListMentorRankByStar(memberType, mainCategoryId);
    }
}
