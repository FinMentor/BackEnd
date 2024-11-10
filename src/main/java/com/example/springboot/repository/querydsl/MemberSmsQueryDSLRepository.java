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

    public List<MemberSmsEntity> selectListMemberByPhone(String phoneFirst, String phoneMiddle) {
        return jpaQueryFactory
                .selectFrom(memberSmsEntity)
                .join(memberSmsEntity.memberEntity, memberEntity)
                .fetchJoin()
                .where(memberSmsEntity.phoneFirst.eq(phoneFirst).and(memberSmsEntity.phoneMiddle.eq(phoneMiddle)))
                .fetch();
    }

    public List<MemberSmsEntity> selectListMemberSmsByPhoneVerificationCode(String phoneFirst, String phoneMiddle, String phoneVerificationCode) {
        return jpaQueryFactory
                .selectFrom(memberSmsEntity)
                .where(memberSmsEntity.phoneFirst.eq(phoneFirst),
                        memberSmsEntity.phoneMiddle.eq(phoneMiddle),
                        memberSmsEntity.phoneVerificationCode.eq(phoneVerificationCode)
                )
                .fetch();
    }
}
