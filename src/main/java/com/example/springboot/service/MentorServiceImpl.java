package com.example.springboot.service;

import com.example.springboot.dao.MainCategoryDAO;
import com.example.springboot.dao.MemberDAO;
import com.example.springboot.dto.MentorDTO;
import com.example.springboot.dto.MentorRecommendDTO;
import com.example.springboot.entity.domain.MainCategoryEntity;
import com.example.springboot.entity.domain.MemberEntity;
import com.example.springboot.exception.FailGetMainCategoryException;
import com.example.springboot.exception.FailGetMemberException;
import com.example.springboot.util.CommonCodeEnum;
import com.example.springboot.util.ExceptionCodeEnum;
import com.example.springboot.util.ResultCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MentorServiceImpl implements MentorService {
    private final MemberDAO memberDAO;
    private final MainCategoryDAO mainCategoryDAO;

    /**
     * 고수 추천
     * <p>
     * 아이템 기반 필터링으로 고수를 추천하는 메소드이다.
     *
     * @param id
     * @param mainCategoryId
     * @return
     * @throws IOException
     * @throws TasteException
     */
    @Override
    public MentorRecommendDTO recommendMentor(String id, Long mainCategoryId) throws IOException, TasteException {
        log.info("recommendMentor id : {}, mainCategoryId : {}", id, mainCategoryId);

        // 멘티멤버 조회
        MemberEntity mentee = memberDAO.findById(id).orElseThrow(() -> new FailGetMemberException(ExceptionCodeEnum.NONEXISTENT_MEMBER));

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(String.valueOf(mainCategoryId), true))) {
            // 멤버리스트 조회 및 고수파일 생성
            memberDAO.selectListMemberByMainCategoryId(CommonCodeEnum.MENTEE.getValue(), mainCategoryId).forEach(menteeMentor -> {
                try {
                    bw.write(menteeMentor[1] + "," + menteeMentor[2] + "," + menteeMentor[3]);
                    bw.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DataModel dataModel = new FileDataModel(new File(String.valueOf(mainCategoryId)));
        UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(dataModel);
        UserNeighborhood userNeighborhood = new ThresholdUserNeighborhood(0.1, userSimilarity, dataModel);
        UserBasedRecommender userBasedRecommender = new GenericUserBasedRecommender(dataModel, userNeighborhood, userSimilarity);
        List<RecommendedItem> recommendedItemList = userBasedRecommender.recommend(mentee.getMemberId(), 3);

        List<Long> itemId = recommendedItemList.isEmpty()
                ? memberDAO.selectListMentorRankByStar(CommonCodeEnum.MENTOR.getValue(), mainCategoryId)
                : recommendedItemList.stream()
                .map(RecommendedItem::getItemID)
                .collect(Collectors.toList());

        return MentorRecommendDTO.builder()
                .mentorDTOList(memberDAO.findById(itemId).stream().map(mentor ->
                        MentorDTO.builder()
                                .memberId(mentor.getMemberId())
                                .name(mentor.getName())
                                .nickname(mentor.getNickname())
                                .profileImageUrl(mentor.getProfileImageUrl())
                                .answerTime(mentor.getAnswerTime())
                                .mainCategoryName(mainCategoryDAO.findById(mainCategoryId).map(MainCategoryEntity::getMainCategoryName).orElseThrow(() -> new FailGetMainCategoryException(ExceptionCodeEnum.NONEXISTENT_CATEGORY))).build()
                ).toList())
                .resultCode(ResultCodeEnum.SUCCESS.getValue())
                .resultMessage(ResultCodeEnum.SUCCESS.getMessage())
                .build();
    }
}
