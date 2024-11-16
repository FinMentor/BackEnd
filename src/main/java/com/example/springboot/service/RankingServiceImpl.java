package com.example.springboot.service;

import com.example.springboot.dao.MemberDAO;
import com.example.springboot.dto.MentorOfThreeDTO;
import com.example.springboot.dto.RankingDTO;
import com.example.springboot.exception.FailGetRankingException;
import com.example.springboot.util.CommonCodeEnum;
import com.example.springboot.util.ExceptionCodeEnum;
import com.example.springboot.util.ResultCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RankingServiceImpl implements RankingService {
    private final MemberDAO memberDAO;

    /**
     * 전체랭킹 조회
     * <p>
     * 멘토전체랭킹을 조회하는 메소드이다.
     *
     * @param term
     * @return
     */
    @Override
    public RankingDTO rankingAll(String term) {
        List<MentorOfThreeDTO> mentorOfThreeDTOList;

        if (CommonCodeEnum.WEEKLY.getValue().equals(term)) {
            // 전체멘토랭킹3순위 주간 조회
            mentorOfThreeDTOList = memberDAO.selectListMentorRankByWeekly(CommonCodeEnum.MENTOR.getValue()).stream().map(
                            mentorOfThreeDTO -> MentorOfThreeDTO.builder()
                                    .memberId((Long) mentorOfThreeDTO[0])
                                    .name((String) mentorOfThreeDTO[1])
                                    .nickname((String) mentorOfThreeDTO[2])
                                    .profileImageUrl((String) mentorOfThreeDTO[3])
                                    .mainCategoryId((Long) mentorOfThreeDTO[4])
                                    .mainCategoryName((String) mentorOfThreeDTO[5])
                                    .averageStar((Double) mentorOfThreeDTO[6]).build())
                    .toList();
        } else if (CommonCodeEnum.MONTHLY.getValue().equals(term)) {
            // 전체멘토랭킹3순위 월간 조회
            mentorOfThreeDTOList = memberDAO.selectListMentorRankByMonthly(CommonCodeEnum.MENTOR.getValue()).stream().map(
                            mentorOfThreeDTO -> MentorOfThreeDTO.builder()
                                    .memberId((Long) mentorOfThreeDTO[0])
                                    .name((String) mentorOfThreeDTO[1])
                                    .nickname((String) mentorOfThreeDTO[2])
                                    .profileImageUrl((String) mentorOfThreeDTO[3])
                                    .mainCategoryId((Long) mentorOfThreeDTO[4])
                                    .mainCategoryName((String) mentorOfThreeDTO[5])
                                    .averageStar((Double) mentorOfThreeDTO[6]).build())
                    .toList();
        } else {
            // 전체멘토랭킹3순위 조회
            mentorOfThreeDTOList = memberDAO.selectListMentorRank(CommonCodeEnum.MENTOR.getValue()).stream().map(
                            mentorOfThreeDTO -> MentorOfThreeDTO.builder()
                                    .memberId((Long) mentorOfThreeDTO[0])
                                    .name((String) mentorOfThreeDTO[1])
                                    .nickname((String) mentorOfThreeDTO[2])
                                    .profileImageUrl((String) mentorOfThreeDTO[3])
                                    .mainCategoryId((Long) mentorOfThreeDTO[4])
                                    .mainCategoryName((String) mentorOfThreeDTO[5])
                                    .averageStar((Double) mentorOfThreeDTO[6]).build())
                    .toList();
        }

        if (mentorOfThreeDTOList.isEmpty()) {
            throw new FailGetRankingException(ExceptionCodeEnum.NONEXISTENT_RANKING);
        }

        return RankingDTO.builder()
                .mentorOfThreeDTOList(mentorOfThreeDTOList)
                .resultCode(ResultCodeEnum.SUCCESS.getValue())
                .resultMessage(ResultCodeEnum.SUCCESS.getMessage()).build();
    }

    /**
     * 전체랭킹 조회 By 카테고리
     * <p>
     * 카테고리별로 전체랭킹을 조회하는 메소드이다.
     *
     * @param mainCategoryId
     * @param term
     * @return
     */
    @Override
    public RankingDTO rankingCategory(Long mainCategoryId, String term) {
        List<MentorOfThreeDTO> mentorOfThreeDTOList;

        if (CommonCodeEnum.WEEKLY.getValue().equals(term)) {
            mentorOfThreeDTOList = memberDAO.selectListMentorCategoryRankByWeekly(CommonCodeEnum.MENTOR.getValue(), mainCategoryId).stream().map(
                            mentorOfThreeDTO -> MentorOfThreeDTO.builder()
                                    .memberId((Long) mentorOfThreeDTO[0])
                                    .name((String) mentorOfThreeDTO[1])
                                    .nickname((String) mentorOfThreeDTO[2])
                                    .profileImageUrl((String) mentorOfThreeDTO[3])
                                    .mainCategoryId((Long) mentorOfThreeDTO[4])
                                    .mainCategoryName((String) mentorOfThreeDTO[5])
                                    .averageStar((Double) mentorOfThreeDTO[6]).build())
                    .toList();
        } else {
            mentorOfThreeDTOList = memberDAO.selectListMentorCategoryRankByMonthly(CommonCodeEnum.MENTOR.getValue(), mainCategoryId).stream().map(
                            mentorOfThreeDTO -> MentorOfThreeDTO.builder()
                                    .memberId((Long) mentorOfThreeDTO[0])
                                    .name((String) mentorOfThreeDTO[1])
                                    .nickname((String) mentorOfThreeDTO[2])
                                    .profileImageUrl((String) mentorOfThreeDTO[3])
                                    .mainCategoryId((Long) mentorOfThreeDTO[4])
                                    .mainCategoryName((String) mentorOfThreeDTO[5])
                                    .averageStar((Double) mentorOfThreeDTO[6]).build())
                    .toList();
        }

        if (mentorOfThreeDTOList.isEmpty()) {
            throw new FailGetRankingException(ExceptionCodeEnum.NONEXISTENT_RANKING);
        }

        return RankingDTO.builder()
                .mentorOfThreeDTOList(mentorOfThreeDTOList)
                .resultCode(ResultCodeEnum.SUCCESS.getValue())
                .resultMessage(ResultCodeEnum.SUCCESS.getMessage()).build();
    }
}
