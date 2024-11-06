package com.example.springboot.repository;

import com.example.springboot.dto.*;
import com.example.springboot.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {
    // 사용자 프로필 정보와 자산 총액을 조회하는 메서드
    MemberEntity selectMemberProfile(@Param("memberId") String memberId);

    int selectMemberById(String memberId);

    int insertMember(MemberDTO memberDTO);

    int insertSocialMember(SocialMemberDTO socialMemberDTO);

    // 아이디찾기
    List<MemberEntity> findMemberByNameAndPhone(FindIdRequestDTO findIdRequestDTO);

    List<MemberEntity> selectMemberByIdAndNameAndPhone(FindPasswordRequestDTO findPasswordRequestDTO);

    int updatePasswordById(@Param("memberId") String memberId, @Param("password") String password);

    // ID로 사용자 정보를 조회
    MemberEntity findByMemberId(@Param("memberId") String memberId);

    // 핸드폰 번호로 사용자 정보 조회
    MemberEntity findMemberByPhoneNumber(@Param("phoneFirst") String phoneFirst, @Param("phoneMiddle") String phoneMiddle);

    void incrementPasswordFailureCount(String memberId);

    void resetPasswordFailureCount(String memberId);

    // survey_status 업데이트
    void updateSurveyStatus(@Param("memberId") String memberId, @Param("surveyStatus") char surveyStatus);

    // survey_status 조회
    String getSurveyStatus(@Param("memberId") String memberId);

    // 특정 사용자 ID로 프로필 정보를 가져옴
    ProfileEditDTO getProfileByMemberId(@Param("memberId") String memberId);

    // 특정 사용자 ID로 프로필 정보를 업데이트함
    void updateProfile(@Param("memberId") String memberId, @Param("profileEditDTO") ProfileEditDTO profileEditDTO);
}
