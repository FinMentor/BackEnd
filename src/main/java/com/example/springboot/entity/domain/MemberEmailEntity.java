package com.example.springboot.entity.domain;

import com.example.springboot.entity.common.CommonColumn;
import com.example.springboot.entity.common.util.ColumnYn;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
@Table(name = "MEMBER_EMAIL")
public class MemberEmailEntity extends CommonColumn {
    @Id
    @Comment("이메일")
    @Column(name = "EMAIL", length = 100, nullable = false, unique = true)
    private String email;

    @Comment("멤버 아이디")
    @Column(name = "MEMBER_ID", length = 50, nullable = false, insertable = false, updatable = false)
    private String memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private MemberEntity memberEntity;

    @Comment("이메일 인증코드")
    @Column(name = "EMAIL_VERIFICATION_CODE", length = 50, nullable = false)
    private String emailVerificationCode;

    @Comment("이메일 인증상태")
    @Column(name = "EMAIL_VERIFIED_STATUS", length = 1, nullable = false)
    @ColumnDefault("'N'")
    @Enumerated(EnumType.STRING)
    private ColumnYn emailVerifiedStatus;
}
