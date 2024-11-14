package com.example.springboot.entity.domain;

import com.example.springboot.entity.common.CommonColumn;
import com.example.springboot.entity.common.util.ColumnYn;
import com.example.springboot.vo.ChatroomGroupVO;
import com.example.springboot.vo.MemberAnswerVO;
import com.example.springboot.vo.MemberCategoryVO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
@Table(name = "MEMBER")
public class MemberEntity extends CommonColumn {
    @Id
    @Comment("멤버 아이디")
    @Column(name = "MEMBER_ID", length = 50, nullable = false, unique = true)
    private String memberId;

    @OneToMany(mappedBy = "memberEntity", fetch = FetchType.LAZY)
    private List<MemberSmsEntity> memberSmsEntityList;

    @OneToMany(mappedBy = "memberEntity", fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList;

    @OneToMany(mappedBy = "memberEntity", fetch = FetchType.LAZY)
    private List<LikeEntity> likeEntityList;

    @OneToMany(mappedBy = "memberEntity", fetch = FetchType.LAZY)
    private List<MemberEmailEntity> memberEmailEntityList;

    @OneToMany(mappedBy = "memberEntity", fetch = FetchType.LAZY)
    private List<PostEntity> postEntityList;

    @OneToOne(mappedBy = "memberEntity", fetch = FetchType.LAZY)
    private MemberCategoryVO memberCategoryVO;

    @OneToMany(mappedBy = "memberEntity", fetch = FetchType.LAZY)
    private List<SettingEntity> settingEntityList;

    @OneToMany(mappedBy = "memberEntity", fetch = FetchType.LAZY)
    private List<NotificationEntity> notificationEntityList;

    @OneToMany(mappedBy = "memberEntity", fetch = FetchType.LAZY)
    private List<SelectedTermsEntity> selectedTermsEntityList;

    @OneToMany(mappedBy = "memberEntity", fetch = FetchType.LAZY)
    private List<MemberAnswerVO> memberAnswerVOList;

    @OneToMany(mappedBy = "memberEntity", fetch = FetchType.LAZY)
    private List<ChatroomGroupVO> chatroomGroupVOList;

    @OneToMany(mappedBy = "followerEntity", fetch = FetchType.LAZY)
    private List<FollowEntity> followerList;

    @OneToMany(mappedBy = "followingEntity", fetch = FetchType.LAZY)
    private List<FollowEntity> followingList;

    @OneToMany(mappedBy = "memberEntity", fetch = FetchType.LAZY)
    private List<MessageEntity> messageEntityList;

    @Comment("비밀번호")
    @Column(name = "PASSWORD", length = 255, nullable = false)
    private String password;

    @Comment("비밀번호 틀린 횟수")
    @Column(name = "PASSWORD_FAILURE_COUNT", nullable = false)
    @ColumnDefault("0")
    private Integer passwordFailureCount;

    @Comment("이름")
    @Column(name = "NAME", length = 10, nullable = false)
    private String name;

    @Comment("생년월일")
    @Column(name = "BIRTH_DATE", nullable = true)
    private LocalDate birthDate;

    @Comment("닉네임")
    @Column(name = "NICKNAME", length = 20, nullable = true, unique = true)
    private String nickname;

    @Comment("회원탈퇴여부")
    @Column(name = "MEMBER_STATUS", length = 1, nullable = false)
    @ColumnDefault("'Y'")
    @Enumerated(EnumType.STRING)
    private ColumnYn memberStatus;

    @Comment("설문조사여부")
    @Column(name = "SURVEY_STATUS", length = 1, nullable = false)
    @ColumnDefault("'N'")
    @Enumerated(EnumType.STRING)
    private ColumnYn surveyStatus;

    @Comment("푸시알림설정")
    @Column(name = "ALARM_STATUS", length = 1, nullable = false)
    @ColumnDefault("'Y'")
    @Enumerated(EnumType.STRING)
    private ColumnYn alarmStatus;

    @Comment("프로필 이미지")
    @Column(name = "PROFILE_IMAGE_URL", length = 255, nullable = true)
    private String profileImageUrl;

    @Comment("자동로그인")
    @Column(name = "AUTO_LOGIN", length = 1, nullable = false)
    @ColumnDefault("'N'")
    @Enumerated(EnumType.STRING)
    private ColumnYn autoLogin;

    @Comment("SNS 유형")
    @Column(name = "SNS_TYPE", length = 10, nullable = true)
    private String snsType;

    @Comment("멤버 유형")
    @Column(name = "MEMBER_TYPE", length = 10, nullable = true)
    private String memberType;

    @Comment("자기소개")
    @Column(name = "INTRODUCE", length = 255, nullable = false)
    private String introduce;

    @Comment("답변 가능한 시간")
    @Column(name = "ANSWER_TIME", length = 10, nullable = true)
    private String answerTime;
}
