package com.example.springboot.entity.domain;

import com.example.springboot.entity.common.CommonColumn;
import com.example.springboot.entity.common.util.ColumnYn;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
@Table(name = "NOTIFICATION")
public class NotificationEntity extends CommonColumn {
    @Id
    @Comment("알림 내역 아이디")
    @Column(name = "NOTIFICATION_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @Comment("멤버 아이디")
    @Column(name = "MEMBER_ID", nullable = false, insertable = false, updatable = false)
    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private MemberEntity memberEntity;

    @Comment("메시지")
    @Column(name = "MESSAGE", nullable = false)
    private String message;

    @Comment("읽음 확인 여부")
    @Column(name = "READED", length = 1, nullable = false)
    @ColumnDefault("'N'")
    @Enumerated(EnumType.STRING)
    private ColumnYn readed;
}
