package com.example.springboot.vo;

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
}
