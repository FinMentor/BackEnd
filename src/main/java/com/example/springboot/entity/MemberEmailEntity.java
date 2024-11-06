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
@Table(name = "MEMBER_EMAIL")
public class MemberEmailEntity {
    @Id
    @Column(name = "EMAIL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String email;

    @Column(name = "MEMBER_ID")
    private String memberId;

    @Column(name = "EMAIL_VERIFICATION_CODE")
    private String emailVerificationCode;

    @Column(name = "EMAIL_VERIFIED_STATUS")
    private Character emailVerifiedStatus;

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

    @OneToOne
    private MemberEntity memberEntity;
}
