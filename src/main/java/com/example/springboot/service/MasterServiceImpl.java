package com.example.springboot.service;

import com.example.springboot.dao.MemberDAO;
import com.example.springboot.dao.querydsl.MemberQueryDSLDAO;
import com.example.springboot.dto.MasterMemberDTO;
import com.example.springboot.dto.MasterRecommendDTO;
import com.example.springboot.entity.domain.MemberEntity;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static org.apache.hadoop.yarn.webapp.hamlet.HamletSpec.InputType.file;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MasterServiceImpl implements MasterService {
    private final MemberQueryDSLDAO memberQueryDSLDAO;
    private final MemberDAO memberDAO;

    /**
     * 고수 추천
     * <p>
     * 아이템 기반 필터링으로 고수를 추천하는 메소드이다.
     *
     * @param mainCategoryId
     * @param answerTime
     * @return
     * @throws IOException
     * @throws TasteException
     */
    @Override
    public MasterRecommendDTO recommendMaster(long mainCategoryId, String answerTime) throws IOException, TasteException {
        AtomicLong memberId = new AtomicLong(0);
        Map<Long, String> members = new HashMap<>();

        // 고수 멤버 조회후 팔로잉 카운트
        List<MasterMemberDTO> masterMemberDTOList = memberQueryDSLDAO.selectListMemberByMemberType(CommonCodeEnum.MASTER.getValue()).stream()
                .collect(Collectors.groupingBy(MemberEntity::getMemberId))
                .values().stream()
                .map(memberEntityList -> {
                    if (memberEntityList.isEmpty()) {
                        return null;
                    }

                    members.put(memberId.incrementAndGet(), memberEntityList.get(0).getMemberId());

                    return MasterMemberDTO.builder()
                            .memberId(memberId.get())
                            .mainCategoryId(memberEntityList.get(0).getMemberCategoryVO() == null ? null : memberEntityList.get(0).getMemberCategoryVO().getMainCategoryId())
                            .answerTime(memberEntityList.get(0).getAnswerTime())
                            .followingCount(memberEntityList.get(0).getFollowingList().isEmpty() ? 0.0 : memberEntityList.get(0).getFollowingList().size()).build();
                })
                .filter(Objects::nonNull)
                .toList();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(String.valueOf(file), true))) {
            masterMemberDTOList.forEach(masterMemberDTO -> {
                try {
                    bw.write(masterMemberDTO.getMainCategoryId() + "," + masterMemberDTO.getMemberId() + "," + masterMemberDTO.getFollowingCount());
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
        List<RecommendedItem> recommendedItemList = userBasedRecommender.recommend(mainCategoryId, 1);

        AtomicLong itemId = new AtomicLong(1);
        recommendedItemList.forEach(recommendedItem -> {
            itemId.set(recommendedItem.getItemID());
        });


        return memberDAO.findById(members.get(itemId.get())).map(memberEntity ->
                        MasterRecommendDTO.builder()
                                .memberId(memberEntity.getMemberId())
                                .name(memberEntity.getName())
                                .nickname(memberEntity.getNickname())
                                .profileImageUrl(memberEntity.getProfileImageUrl())
                                .resultCode(ResultCodeEnum.SUCCESS.getValue())
                                .resultMessage(ResultCodeEnum.SUCCESS.getMessage()).build())
                .orElse(null);
    }
}
