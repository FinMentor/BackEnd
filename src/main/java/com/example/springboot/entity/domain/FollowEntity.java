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
@Table(name = "FOLLOW")
public class FollowEntity extends CommonColumn {
    @Id
    @Comment("팔로우 아이디")
    @Column(name = "FOLLOW_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId;

    @Comment("팔로워 아이디")
    @Column(name = "FOLLOWER_ID", nullable = false, insertable = false, updatable = false)
    private Long followerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FOLLOWER_ID")
    private MemberEntity followerEntity;

    @Comment("팔로잉 아이디")
    @Column(name = "FOLLOWING_ID", nullable = false, insertable = false, updatable = false)
    private Long followingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FOLLOWING_ID")
    private MemberEntity followingEntity;
}
