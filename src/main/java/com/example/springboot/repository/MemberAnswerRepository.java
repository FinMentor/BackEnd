package com.example.springboot.repository;

import com.example.springboot.vo.MemberAnswerVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface MemberAnswerRepository extends JpaRepository<MemberAnswerVO, Integer> {
    // 사용자 답변 입력 및 업데이트
    int insertMemberAnswer(MemberAnswerVO memberAnswerVO);

    int updateMemberAnswer(MemberAnswerVO memberAnswerVO);

    // 추가: 사용자 답변 조회 메소드(중복에 따라 update insert 구분하기 위해)
    MemberAnswerVO selectMemberAnswer(@Param("memberId") String memberId, @Param("questionId") long questionId);

    // 사용자 총 점수 계산 메서드
    int getTotalScore(@Param("memberId") String memberId);

    int getTotalAnsweredQuestions(String memberId);
}
