package com.example.springboot.repository.querydsl;

import com.example.springboot.entity.domain.MemberEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.springboot.entity.domain.QFollowEntity.followEntity;
import static com.example.springboot.entity.domain.QMemberEntity.memberEntity;
import static com.example.springboot.vo.QMemberCategoryVO.memberCategoryVO;

@Repository
@RequiredArgsConstructor
public class MemberQueryDSLRepository {
    private final JPAQueryFactory jpaQueryFactory;

    /**
     * 멤버리스트 조회
     * <p>
     * 멤버 유형으로 멤버리스트를 조회하는 메소드이다.
     *
     * @param memberType
     * @return
     */
    public List<MemberEntity> selectListMemberByMemberType(String memberType) {
        return jpaQueryFactory
                .selectFrom(memberEntity)
                .join(memberEntity.memberCategoryVO, memberCategoryVO)
                .fetchJoin()
                .leftJoin(memberEntity.followingList, followEntity)
                .fetchJoin()
                .where(memberEntity.memberType.eq(memberType))
                .fetch();
    }
}
