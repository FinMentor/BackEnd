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
@IdClass(MemberSmsId.class)
@Table(name = "MEMBER_SMS")
public class MemberSmsEntity {
    @Id
    @Column(name = "PHONE_FIRST")
    private String phoneFirst;

    @Id
    @Column(name = "PHONE_MIDDLE")
    private String phoneMiddle;

    @Id
    @Column(name = "PHONE_LAST")
    private String phoneLast;

    @Column(name = "MEMBER_ID")
    private String memberId;

    @Column(name = "PHONE_VERIFICATION_CODE")
    private String phoneVerificationCode;

    @Column(name = "PHONE_VERIFIED_STATUS")
    private Character phoneVerifiedStatus;

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
