package com.example.springboot.vo;

import com.example.springboot.entity.MemberEntity;
import com.example.springboot.entity.QuestionEntity;
import com.example.springboot.entity.QuestionOptionEntity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@IdClass(MemberAnswerId.class)
@Table(name = "MEMBER_ANSWER")
public class MemberAnswerVO {
    @Id
    @Column(name = "MEMBER_ID")
    private String memberId;

    @Id
    @Column(name = "QUESTION_ID")
    private Long questionId;

    @Id
    @Column(name = "QUESTION_OPTION_ID")
    private Long questionOptionId;

    @Column(name = "CREATED_AT")
    private Timestamp createdAt;

    @Column(name = "UPDATED_AT")
    private Timestamp updatedAt;

    @PrePersist
    public void createdAt() {
        this.createdAt = Timestamp.from(Instant.now());
    }

    @PreUpdate
    public void updatedAt() {
        this.updatedAt = Timestamp.from(Instant.now());
    }

    @ManyToOne
    private MemberEntity memberEntity;

    @OneToOne
    private QuestionEntity questionEntity;

    @OneToOne
    private QuestionOptionEntity questionOptionEntity;
}
