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
@Table(name = "SELECTED_TERMS")
public class SelectedTermsEntity {
    @Id
    @Column(name = "SELECTED_TERMS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long selectedTermsId;

    @Column(name = "MEMBER_ID")
    private String memberId;

    @Column(name = "TERMS_OF_USE_ID")
    private Long termsOfUseId;

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
    private TermsOfUseEntity termsOfUseEntity;
}
