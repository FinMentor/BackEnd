package com.example.springboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "INTEREST_CATEGORY")
public class InterestCategoryEntity {
    @Id
    @Column(name = "INTEREST_CATEGORY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interestCategoryId;

    @Column(name = "MEMBER_ID")
    private String memberId;

    @Column(name = "SUB_CATEGORY_ID")
    private Long subCategoryId;

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
    private SubCategoryEntity subCategoryEntity;

    @ManyToOne
    private MemberEntity memberEntity;
}
