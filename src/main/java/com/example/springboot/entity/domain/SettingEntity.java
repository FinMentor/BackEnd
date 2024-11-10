package com.example.springboot.entity.domain;

import com.example.springboot.entity.common.CommonColumn;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "SETTING")
public class SettingEntity extends CommonColumn {
    @Id
    @Comment("설정 아이디")
    @Column(name = "SETTING_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long settingId;

    @Comment("멤버 아이디")
    @Column(name = "MEMBER_ID", length = 50, nullable = false, insertable = false, updatable = false)
    private String memberId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private MemberEntity memberEntity;
}
