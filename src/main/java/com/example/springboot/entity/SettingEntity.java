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
@Table(name = "SETTING")
public class SettingEntity {
    @Id
    @Column(name = "SETTING_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long settingId;

    @Column(name = "MEMBER_ID")
    private String memberId;

    @Column(name = "MODE_CODE")
    private Character modeCode;

    @Column(name = "HOME_NAVIGATION_CODE")
    private Character homeNavigationCode;

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
