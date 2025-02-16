package com.example.springboot.entity.domain;

import com.example.springboot.entity.common.CommonColumn;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Builder
@Table(name = "POST")
public class PostEntity extends CommonColumn {
    @Id
    @Comment("게시 아이디")
    @Column(name = "POST_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @OneToMany(mappedBy = "postEntity", fetch = FetchType.LAZY)
    List<CommentEntity> commentEntityList;

    @OneToMany(mappedBy = "postEntity", fetch = FetchType.LAZY)
    List<LikeEntity> likeEntityList;

    @Comment("멤버 아이디")
    @Column(name = "MEMBER_ID", nullable = false, insertable = false, updatable = false)
    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private MemberEntity memberEntity;

    @Comment("메인 카테고리 아이디")
    @Column(name = "MAIN_CATEGORY_ID", nullable = false, insertable = false, updatable = false)
    private Long mainCategoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAIN_CATEGORY_ID")
    private MainCategoryEntity mainCategoryEntity;

    @Comment("제목")
    @Column(name = "TITLE", length = 100, nullable = false)
    private String title;

    @Comment("내용")
    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Comment("게시 이미지")
    @Column(name = "POST_IMAGE_URL", length = 255, nullable = true)
    private String postImageUrl;

    @Comment("게시 유형")
    @Column(name = "POST_TYPE", length = 20, nullable = false)
    private String postType;

    @Comment("조회수")
    @Column(name = "VIEW_COUNT", nullable = false)
    @ColumnDefault("0")
    private Long viewCount;

    @Comment("좋아요수")
    @Column(name = "LIKE_COUNT", nullable = false)
    @ColumnDefault("0")
    private Long likeCount;
}
