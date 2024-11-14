package com.example.springboot.dao;

import com.example.springboot.entity.domain.MemberEntity;
import com.example.springboot.exception.ErrorRequiredValueValidationException;
import com.example.springboot.repository.MemberRepository;
import com.example.springboot.util.ExceptionCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        if (memberEntity.getPassword() == null || memberEntity.getPassword().isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("password는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (memberEntity.getName() == null || memberEntity.getName().isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("name은 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (memberEntity.getIntroduce() == null || memberEntity.getIntroduce().isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("introduce는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("save memberEntity : {}", memberEntity);

        return memberRepository.save(memberEntity);
    }

    /**
     * 멤버 조회
     * <p>
     * PK로 멤버 테이블을 조회하는 메소드이다.
     *
     * @param id
     * @return
     */
    @Override
    public Optional<MemberEntity> findById(String id) {
        if (id == null || id.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("id는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("findById id : {}", id);

        return memberRepository.findById(id);
    }

    /**
     * 멤버리스트 조회
     * <p>
     * 멤버아이디리스트에 포함되는 모든 멤버를 조회한다.
     *
     * @param memberIdList
     * @return
     */
    @Override
    public List<MemberEntity> findById(List<Long> memberIdList) {
        if (memberIdList == null || memberIdList.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberIdList는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("findById memberIdList : {}", memberIdList);

        return memberRepository.findById(memberIdList);
    }

    /**
     * 비밀번호실패횟수 초기화
     *
     * 멤버아이디로 비밀번호실패횟수를 초기화하는 메소드이다.
     *
     * @param id
     * @return
     */
    @Override
    public int resetPasswordFailureCount(String id) {
        if (id == null || id.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("id는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("resetPasswordFailureCount id : {}", id);

        return memberRepository.resetPasswordFailureCount(id);
    }

    /**
     * 비밀번호 변경
     * <p>
     * 멤버 테이블의 비밀번호를 변경하는 메소드이다.
     *
     * @param id
     * @param password
     * @return
     */
    @Override
    public int save(String id, String password) {
        if (id == null || id.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("id는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (password == null || password.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("password는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("save id : {}, password : {}", id, password);

        return memberRepository.save(id, password);
    }

    @Override
    public List<Object[]> selectListMemberByMainCategoryId(String memberType, Long mainCategoryId) {
        if (memberType == null || memberType.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberType은 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (mainCategoryId == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("mainCategoryId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("selectListMemberByMemberType memberType : {}, mainCategoryId : {}", memberType, mainCategoryId);

        return memberRepository.selectListMemberByMainCategoryId(memberType, mainCategoryId);
    }

    @Override
    public List<Long> selectListMentorRankByStar(String memberType, Long mainCategoryId) {
        if (memberType == null || memberType.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberType은 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (mainCategoryId == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("mainCategoryId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("selectListMentorRankByStar memberType : {}, mainCategoryId : {}", memberType, mainCategoryId);

        return memberRepository.selectListMentorRankByStar(memberType, mainCategoryId);
    }
}
