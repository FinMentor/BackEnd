package com.example.springboot.entity;

import com.example.springboot.util.CommonCodeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "MEMBER")
public class MemberEntity {
    @Id
    @Column(name = "MEMBER_ID")
    private String memberId;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "PASSWORD_FAILURE_COUNT")
    private Integer passwordFailureCount;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "MEMBER_STATUS")
    private Character memberStatus;

    @Column(name = "SURVEY_STATUS")
    private Character surveyStatus;

    @Column(name = "ALARM_STATUS")
    private Character alarmStatus;

    @Column(name = "PROFILE_IMAGE_URL")
    private String profileImageUrl;

    @Column(name = "AUTO_LOGIN")
    private Character autoLogin;

    @Column(name = "SNS_TYPE")
    private String snsType;

    @Column(name = "MEMBER_TYPE")
    private String memberType;

    @Column(name = "CREATED_AT")
    private Timestamp createdAt;

    @Column(name = "UPDATED_AT")
    private Timestamp updatedAt;

    @PrePersist
    public void setDefaultValues() {
        this.createdAt = Timestamp.from(Instant.now());
        this.passwordFailureCount = 0;
        this.memberStatus = CommonCodeEnum.YES.getValue().charAt(0);
        this.surveyStatus = CommonCodeEnum.NO.getValue().charAt(0);
        this.alarmStatus = CommonCodeEnum.YES.getValue().charAt(0);
        this.autoLogin = CommonCodeEnum.NO.getValue().charAt(0);
    }

    @PreUpdate
    public void updatedAt() {
        this.updatedAt = Timestamp.from(Instant.now());
    }
}
