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
@Table(name = "FILE")
public class FileEntity extends CommonColumn {
    @Id
    @Comment("파일 아이디")
    @Column(name = "FILE_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;

    @Comment("멤버 아이디")
    @Column(name = "MEMBER_ID", nullable = false, insertable = false, updatable = false)
    private Long memberId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private MemberEntity memberEntity;

    @Comment("파일 경로")
    @Column(name = "FILE_PATH", length = 100, nullable = false)
    private String filePath;
}
