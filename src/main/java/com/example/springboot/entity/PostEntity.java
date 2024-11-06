package com.example.springboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "POST")
public class PostEntity {
    @Id
    @Column(name = "POST_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(name = "MEMBER_ID")
    private String memberId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "MAIN_CATEGORY_ID")
    private Integer mainCategoryId;

    @Column(name = "VIEW_COUNT")
    private Integer viewCount;

    @Column(name = "LIKE_COUNT")
    private Integer likeCount;

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

    @ManyToOne
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "postEntity")
    List<CommentEntity> commentEntityList;

    @OneToMany(mappedBy = "postEntity")
    List<LikeEntity> likeEntityList;
}
