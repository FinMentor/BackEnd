package com.example.springboot.repository.querydsl;

import com.example.springboot.entity.domain.MemberSmsEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.springboot.entity.domain.QMemberEntity.memberEntity;
import static com.example.springboot.entity.domain.QMemberSmsEntity.memberSmsEntity;

@Repository
@RequiredArgsConstructor
public class MemberSmsQueryDSLRepository {
    private final JPAQueryFactory jpaQueryFactory;

    /**
     * 휴대폰으로 멤버리스트 조회
     * <p>
     * 휴대폰 정보로 멤버리스트를 조회하는 메소드이다.
     *
     * @param phoneFirst
     * @param phoneMiddle
     * @return
     */
    public List<MemberSmsEntity> selectListMemberByPhone(String phoneFirst, String phoneMiddle) {
        return jpaQueryFactory
                .selectFrom(memberSmsEntity)
                .join(memberSmsEntity.memberEntity, memberEntity)
                .fetchJoin()
                .where(memberSmsEntity.phoneFirst.eq(phoneFirst).and(memberSmsEntity.phoneMiddle.eq(phoneMiddle)))
                .fetch();
    }

    /**
     * 휴대폰인증코드로 멤버SMS리스트 조회
     * <p>
     * 휴대폰과 휴대폰인증코드로 멤버SMS리스트를 조회하는 메소드이다.
     *
     * @param phoneFirst
     * @param phoneMiddle
     * @param phoneVerificationCode
     * @return
     */
    public List<MemberSmsEntity> selectListMemberSmsByPhoneVerificationCode(String phoneFirst, String phoneMiddle, String phoneVerificationCode) {
        return jpaQueryFactory
                .selectFrom(memberSmsEntity)
                .where(memberSmsEntity.phoneFirst.eq(phoneFirst),
                        memberSmsEntity.phoneMiddle.eq(phoneMiddle),
                        memberSmsEntity.phoneVerificationCode.eq(phoneVerificationCode)
                )
                .fetch();
    }

    /**
     * 아이디, 이름, 휴대폰으로 멤버리스트 조회
     * <p>
     * 아이디와 이름, 휴대폰으로 멤버리스트를 조회하는 메소드이다.
     *
     * @param id
     * @param name
     * @param phoneFirst
     * @param phoneMiddle
     * @return
     */
    public List<MemberSmsEntity> selectListMemberByIdAndNameAndPhone(String id, String name, String phoneFirst, String phoneMiddle) {
        return jpaQueryFactory
                .selectFrom(memberSmsEntity)
                .join(memberSmsEntity.memberEntity, memberEntity)
                .fetchJoin()
                .where(memberSmsEntity.phoneFirst.eq(phoneFirst).and(memberSmsEntity.phoneMiddle.eq(phoneMiddle)).and(memberEntity.id.eq(id)).and(memberEntity.name.eq(name)))
                .fetch();
    }
}
