package com.example.springboot.entity.domain;

import com.example.springboot.entity.common.CommonColumn;
import com.example.springboot.vo.MemberCategoryVO;
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
@Table(name = "MAIN_CATEGORY")
public class MainCategoryEntity extends CommonColumn {
    @Id
    @Comment("메인 카테고리 아이디")
    @Column(name = "MAIN_CATEGORY_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mainCategoryId;

    @OneToOne(mappedBy = "mainCategoryEntity", fetch = FetchType.LAZY)
    private MemberCategoryVO memberCategoryVO;

    @OneToOne(mappedBy = "mainCategoryEntity", fetch = FetchType.LAZY)
    private PostEntity postEntity;

    @Comment("메인 카테고리 이름")
    @Column(name = "MAIN_CATEGORY_NAME", length = 20, nullable = false)
    private String mainCategoryName;
}
