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
@Table(name = "COMMENT")
public class CommentEntity extends CommonColumn {
    @Id
    @Comment("댓글 아이디")
    @Column(name = "COMMENT_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Comment("멤버 아이디")
    @Column(name = "MEMBER_ID", nullable = false, insertable = false, updatable = false)
    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private MemberEntity memberEntity;

    @Comment("게시 아이디")
    @Column(name = "POST_ID", nullable = false, insertable = false, updatable = false)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private PostEntity postEntity;

    @Comment("내용")
    @Column(name = "CONTENT", nullable = false)
    private String content;
}
