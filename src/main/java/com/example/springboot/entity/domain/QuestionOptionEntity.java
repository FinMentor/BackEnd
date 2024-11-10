package com.example.springboot.entity.domain;

import com.example.springboot.entity.common.CommonColumn;
import com.example.springboot.vo.MemberAnswerVO;
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
@Table(name = "QUESTION_OPTION")
public class QuestionOptionEntity extends CommonColumn {
    @Id
    @Comment("옵션 아이디")
    @Column(name = "QUESTION_OPTION_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionOptionId;

    @OneToOne(mappedBy = "questionOptionEntity", fetch = FetchType.LAZY)
    private MemberAnswerVO memberAnswerVO;

    @Comment("질문 아이디")
    @Column(name = "QUESTION_ID", nullable = false, insertable = false, updatable = false)
    private Long questionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID")
    private QuestionEntity questionEntity;

    @Comment("답변 선택지 내용")
    @Column(name = "CONTENT", length = 100, nullable = false)
    private String content;
}
