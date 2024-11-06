package com.example.springboot.entity;

import com.example.springboot.vo.MemberAnswerVO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "MEMBER")
public class MemberEntity {
    @Id
    @Column(name = "MEMBER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "REPORT_TIME")
    private Time reportTime;

    @Column(name = "PROFILE_IMAGE_URL")
    private String profileImageUrl;

    @Column(name = "AUTO_LOGIN")
    private Character autoLogin;

    @Column(name = "SNS_TYPE")
    private String snsType;

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

    @OneToOne(mappedBy = "memberEntity")
    private SettingEntity settingEntity;

    @OneToOne(mappedBy = "memberEntity")
    private MemberSmsEntity memberSmsEntity;

    @OneToOne(mappedBy = "memberEntity")
    private MemberEmailEntity memberEmailEntity;

    @OneToMany(mappedBy = "memberEntity")
    private List<NotificationEntity> notificationEntityList;

    @OneToMany(mappedBy = "memberEntity")
    private List<SelectedTermsEntity> selectedTermsEntityList;

    @OneToMany(mappedBy = "memberEntity")
    private List<MemberAnswerVO> memberAnswerVOList;

    @OneToMany(mappedBy = "memberEntity")
    private List<InterestCategoryEntity> interestCategoryEntityList;

    @OneToMany(mappedBy = "memberEntity")
    private List<PostEntity> postEntityList;

    @OneToMany(mappedBy = "memberEntity")
    private List<ChatroomEntity> chatroomEntityList;
}
