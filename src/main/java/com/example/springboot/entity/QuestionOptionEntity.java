package com.example.springboot.entity;

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
@Table(name = "QUESTION_OPTION")
public class QuestionOptionEntity {
    @Id
    @Column(name = "QUESTION_OPTION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionOptionId;

    @Column(name = "QUESTION_ID")
    private Long questionId;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "SCORE")
    private Double score;

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
