package com.example.springboot.entity.domain;

import com.example.springboot.entity.common.CommonColumn;
import com.example.springboot.entity.common.util.ColumnYn;
import com.example.springboot.entity.id.MemberSmsId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(MemberSmsId.class)
@Table(name = "MEMBER_SMS")
public class MemberSmsEntity extends CommonColumn {
    @Id
    @Comment("휴대폰 첫자리")
    @Column(name = "PHONE_FIRST", length = 3, nullable = false)
    private String phoneFirst;

    @Id
    @Comment("휴대폰 중간자리")
    @Column(name = "PHONE_MIDDLE", length = 4, nullable = false)
    private String phoneMiddle;

    @Id
    @Comment("휴대폰 뒷자리")
    @Column(name = "PHONE_LAST", length = 100, nullable = false)
    private String phoneLast;

    @Comment("멤버 아이디")
    @Column(name = "MEMBER_ID", nullable = false, insertable = false, updatable = false)
    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private MemberEntity memberEntity;

    @Comment("휴대폰 인증코드")
    @Column(name = "PHONE_VERIFICATION_CODE", length = 6, nullable = false)
    private String phoneVerificationCode;

    @Comment("휴대폰 인증상태")
    @Column(name = "PHONE_VERIFIED_STATUS", length = 1, nullable = false)
    @ColumnDefault("'N'")
    @Enumerated(EnumType.STRING)
    private ColumnYn phoneVerifiedStatus;
}
