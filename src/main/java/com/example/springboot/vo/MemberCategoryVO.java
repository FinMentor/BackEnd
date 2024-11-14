package com.example.springboot.vo;

import com.example.springboot.entity.common.CommonColumn;
import com.example.springboot.entity.domain.MainCategoryEntity;
import com.example.springboot.entity.domain.MemberEntity;
import com.example.springboot.vo.id.MemberCategoryId;
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
@IdClass(MemberCategoryId.class)
@Table(name = "MEMBER_CATEGORY")
public class MemberCategoryVO extends CommonColumn {
    @Id
    @Comment("멤버 아이디")
    @Column(name = "MEMBER_ID", length = 50, nullable = false, insertable = false, updatable = false)
    private String memberId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private MemberEntity memberEntity;

    @Id
    @Comment("메인 카테고리 아이디")
    @Column(name = "MAIN_CATEGORY_ID", nullable = false, insertable = false, updatable = false)
    private Long mainCategoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAIN_CATEGORY_ID")
    private MainCategoryEntity mainCategoryEntity;
}
