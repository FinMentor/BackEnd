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
@Table(name = "LIKE")
public class LikeEntity extends CommonColumn {
    @Id
    @Comment("좋아요 아이디")
    @Column(name = "LIKE_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @Comment("멤버 아이디")
    @Column(name = "MEMBER_ID", length = 50, nullable = false, insertable = false, updatable = false)
    private String memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private MemberEntity memberEntity;

    @Comment("게시 아이디")
    @Column(name = "POST_ID", nullable = false, insertable = false, updatable = false)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private PostEntity postEntity;
}
