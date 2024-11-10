package com.example.springboot.entity.domain;

import com.example.springboot.entity.common.CommonColumn;
import com.example.springboot.vo.MemberAnswerVO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "QUESTION")
public class QuestionEntity extends CommonColumn {
    @Id
    @Comment("질문 아이디")
    @Column(name = "QUESTION_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @OneToOne(mappedBy = "questionEntity", fetch = FetchType.LAZY)
    private MemberAnswerVO memberAnswerVO;

    @OneToMany(mappedBy = "questionEntity", fetch = FetchType.LAZY)
    private List<QuestionOptionEntity> questionOptionEntityList;

    @Comment("질문 내용")
    @Column(name = "CONTENT", length = 100, nullable = false)
    private String content;
}
