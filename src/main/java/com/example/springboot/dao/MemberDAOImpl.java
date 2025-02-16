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
     * 멤버 조회
     * <p>
     * 멤버아이디로 멤버를 조회하는 메소드이다.
     *
     * @param memberId
     * @return
     */
    @Override
    public Optional<MemberEntity> findById(Long memberId) {
        if (memberId == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("findById memberId : {}", memberId);

        return memberRepository.findById(memberId);
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

    /**
     * 멤버리스트 조회
     * <p>
     * 메인카테고리아이디로 멤버리스트를 조회하는 메소드이다.
     *
     * @param memberType
     * @param mainCategoryId
     * @return
     */
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

    /**
     * 멘토랭킹리스트 조회
     * <p>
     * 별점으로 멘토랭킹리스트를 조회하는 메소드이다.
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

        return memberRepository.selectListMentorRankByStar(memberType, mainCategoryId);
    }

    /**
     * 멘토랭킹리스트 조회 By 주간
     * <p>
     * 주간 별점기준 멘토3순위 랭킹리스트를 조회하는 메소드이다.
     *
     * @param memberType
     * @return
     */
    @Override
    public List<Object[]> selectListMentorRankByWeekly(String memberType) {
        if (memberType == null || memberType.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberType은 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("selectListMentorRankByWeekly memberType : {}", memberType);

        return memberRepository.selectListMentorRankByWeekly(memberType);
    }

    /**
     * 멘토랭킹리스트 조회 By 월간
     * <p>
     * 월간 별점기준 멘토3순위 랭킹리스트를 조회하는 메소드이다.
     *
     * @param memberType
     * @return
     */
    @Override
    public List<Object[]> selectListMentorRankByMonthly(String memberType) {
        if (memberType == null || memberType.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberType은 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("selectListMentorRankByMonthly memberType : {}", memberType);

        return memberRepository.selectListMentorRankByMonthly(memberType);
    }

    /**
     * 멘토랭킹리스트 조회
     * <p>
     * 별점기준 멘토3순위 랭킹리스트를 조회하는 메소드이다.
     *
     * @param memberType
     * @return
     */
    @Override
    public List<Object[]> selectListMentorRank(String memberType) {
        if (memberType == null || memberType.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberType은 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("selectListMentorRank memberType : {}", memberType);

        return memberRepository.selectListMentorRank(memberType);
    }

    /**
     * 카테고리 멘토랭킹리스트 조회 By 주간
     * <p>
     * 주간 카테고리 별점기준 멘토3순위 랭킹리스트를 조회하는 메소드이다.
     *
     * @param memberType
     * @param mainCategoryId
     * @return
     */
    @Override
    public List<Object[]> selectListMentorCategoryRankByWeekly(String memberType, Long mainCategoryId) {
        if (memberType == null || memberType.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberType은 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (mainCategoryId == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("mainCategoryId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("selectListMentorCategoryRankByWeekly memberType : {}, mainCategoryId : {}", memberType, mainCategoryId);

        return memberRepository.selectListMentorCategoryRankByWeekly(memberType, mainCategoryId);
    }

    /**
     * 카테고리 멘토랭킹리스트 조회 By 월간
     * <p>
     * 월간 카테고리 별점기준 멘토3순위 랭킹리스트를 조회하는 메소드이다.
     *
     * @param memberType
     * @param mainCategoryId
     * @return
     */
    @Override
    public List<Object[]> selectListMentorCategoryRankByMonthly(String memberType, Long mainCategoryId) {
        if (memberType == null || memberType.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberType은 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (mainCategoryId == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("mainCategoryId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("selectListMentorCategoryRankByMonthly memberType : {}, mainCategoryId : {}", memberType, mainCategoryId);

        return memberRepository.selectListMentorCategoryRankByMonthly(memberType, mainCategoryId);
    }
}
