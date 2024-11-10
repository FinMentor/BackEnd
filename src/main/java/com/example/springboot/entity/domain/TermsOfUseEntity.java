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

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "TERMS_OF_USE")
public class TermsOfUseEntity extends CommonColumn {
    @Id
    @Comment("이용 약관 아이디")
    @Column(name = "TERMS_OF_USE_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long termsOfUseId;

    @OneToOne(mappedBy = "termsOfUseEntity", fetch = FetchType.LAZY)
    private SelectedTermsEntity selectedTermsEntity;

    @Comment("제목")
    @Column(name = "TITLE", length = 50, nullable = false)
    private String title;

    @Comment("내용")
    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Comment("필수/선택 약관 구분")
    @Column(name = "REQUIRED", length = 1, nullable = false)
    @ColumnDefault("'N'")
    @Enumerated(EnumType.STRING)
    private ColumnYn required;
}
