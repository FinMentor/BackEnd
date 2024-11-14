package com.example.springboot.service;

import com.example.springboot.dao.MemberDAO;
import com.example.springboot.dao.querydsl.MemberQueryDSLDAO;
import com.example.springboot.dto.MentorDTO;
import com.example.springboot.dto.MentorRecommendDTO;
import com.example.springboot.util.CommonCodeEnum;
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
import java.util.ArrayList;
import java.util.List;

import static org.apache.hadoop.yarn.webapp.hamlet.HamletSpec.InputType.file;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MentorServiceImpl implements MentorService {
    private final MemberQueryDSLDAO memberQueryDSLDAO;
    private final MemberDAO memberDAO;

    /**
     * 고수 추천
     * <p>
     * 아이템 기반 필터링으로 고수를 추천하는 메소드이다.
     *
     * @param mainCategoryId
     * @return
     * @throws IOException
     * @throws TasteException
     */
    @Override
    public MentorRecommendDTO recommendMentor(Long mainCategoryId) throws IOException, TasteException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(String.valueOf(file), true))) {
            // 멤버리스트 조회 및 고수파일 생성
            memberQueryDSLDAO.selectListMemberByMainCategoryId(CommonCodeEnum.MENTEE.getValue(), mainCategoryId).forEach(mentorMemberDTO -> {
                try {
                    bw.write(mentorMemberDTO.getMenteeId() + "," + mentorMemberDTO.getMentorId() + "," + mentorMemberDTO.getStar());
                    bw.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);  // 예외가 발생하면 런타임 예외로 처리
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DataModel dataModel = new FileDataModel(new File(String.valueOf(file)));
        UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(dataModel);
        UserNeighborhood userNeighborhood = new ThresholdUserNeighborhood(0.1, userSimilarity, dataModel);
        UserBasedRecommender userBasedRecommender = new GenericUserBasedRecommender(dataModel, userNeighborhood, userSimilarity);
        List<RecommendedItem> recommendedItemList = userBasedRecommender.recommend(mainCategoryId, 3);

        List<Long> itemId = new ArrayList<>();
        recommendedItemList.forEach(recommendedItem -> {
            itemId.add(recommendedItem.getItemID());
        });

        return MentorRecommendDTO.builder()
                .mentorDTOList(memberDAO.findById(itemId).stream().map(memberEntity ->
                        MentorDTO.builder()
                                .id(memberEntity.getId())
                                .name(memberEntity.getName())
                                .nickname(memberEntity.getNickname())
                                .profileImageUrl(memberEntity.getProfileImageUrl()).build()
                ).toList())
                .resultCode(ResultCodeEnum.SUCCESS.getValue())
                .resultMessage(ResultCodeEnum.SUCCESS.getMessage())
                .build();
    }
}
