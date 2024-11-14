package com.example.springboot.entity.domain;

import com.example.springboot.entity.common.CommonColumn;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "SELECTED_TERMS")
public class SelectedTermsEntity extends CommonColumn {
    @Id
    @Comment("이용 약관 동의 내역 아이디")
    @Column(name = "SELECTED_TERMS_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long selectedTermsId;

    @Comment("멤버 아이디")
    @Column(name = "MEMBER_ID", nullable = false, insertable = false, updatable = false)
    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private MemberEntity memberEntity;

    @Comment("이용 약관 아이디")
    @Column(name = "TERMS_OF_USE_ID", nullable = false, insertable = false, updatable = false)
    private Long termsOfUseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TERMS_OF_USE_ID")
    private TermsOfUseEntity termsOfUseEntity;
}
