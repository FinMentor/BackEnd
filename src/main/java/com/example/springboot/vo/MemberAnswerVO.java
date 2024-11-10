package com.example.springboot.vo;

import com.example.springboot.entity.common.CommonColumn;
import com.example.springboot.entity.domain.MemberEntity;
import com.example.springboot.entity.domain.QuestionEntity;
import com.example.springboot.entity.domain.QuestionOptionEntity;
import com.example.springboot.vo.id.MemberAnswerId;
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
@IdClass(MemberAnswerId.class)
@Table(name = "MEMBER_ANSWER")
public class MemberAnswerVO extends CommonColumn {
    @Id
    @Comment("멤버 아이디")
    @Column(name = "MEMBER_ID", length = 50, nullable = false, insertable = false, updatable = false)
    private String memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private MemberEntity memberEntity;

    @Id
    @Comment("질문 아이디")
    @Column(name = "QUESTION_ID", nullable = false, insertable = false, updatable = false)
    private Long questionId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID")
    private QuestionEntity questionEntity;

    @Id
    @Comment("옵션 아이디")
    @Column(name = "QUESTION_OPTION_ID", nullable = false, insertable = false, updatable = false)
    private Long questionOptionId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_OPTION_ID")
    private QuestionOptionEntity questionOptionEntity;
}
