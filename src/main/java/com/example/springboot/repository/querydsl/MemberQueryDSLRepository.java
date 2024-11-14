package com.example.springboot.repository.querydsl;

import com.example.springboot.dto.MenteeMentorDTO;
import com.example.springboot.vo.QChatroomGroupVO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.springboot.entity.domain.QMemberEntity.memberEntity;
import static com.example.springboot.vo.QMemberCategoryVO.memberCategoryVO;

@Repository
@RequiredArgsConstructor
public class MemberQueryDSLRepository {
    private final JPAQueryFactory jpaQueryFactory;

    /**
     * 멤버리스트 조회
     * <p>
     * 멤버유형 및 메인카테고리아이디로 멤버리스트를 조회하는 메소드이다.
     *
     * @param memberType
     * @param mainCategoryId
     * @return
     */
    public List<MenteeMentorDTO> selectListMemberByMemberType(String memberType, Long mainCategoryId) {
        QChatroomGroupVO chatroomGroupVO1 = new QChatroomGroupVO("chatroomGroupVO1");
        QChatroomGroupVO chatroomGroupVO2 = new QChatroomGroupVO("chatroomGroupVO2");

        return jpaQueryFactory
                .select(Projections.constructor(
                        MenteeMentorDTO.class,
                        memberCategoryVO.mainCategoryId,
                        chatroomGroupVO1.memberId,
                        chatroomGroupVO2.memberId,
                        chatroomGroupVO1.star
                ))
                .from(memberEntity)
                .join(memberEntity.chatroomGroupVOList, chatroomGroupVO1)
                .join(chatroomGroupVO1.chatroomEntity, chatroomGroupVO2.chatroomEntity)
                .join(memberEntity.memberCategoryVO, memberCategoryVO)
                .where(
                        chatroomGroupVO1.memberId.ne(chatroomGroupVO2.memberId)
                                .and(chatroomGroupVO1.star.isNotNull())
                                .and(memberEntity.memberType.eq(memberType))
                                .and(memberCategoryVO.mainCategoryId.eq(mainCategoryId))
                )
                .fetch();
    }
}
