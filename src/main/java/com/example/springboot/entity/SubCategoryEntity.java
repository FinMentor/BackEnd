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
@Table(name = "SUB_CATEGORY")
public class SubCategoryEntity {
    @Id
    @Column(name = "SUB_CATEGORY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subCategoryId;

    @Column(name = "MAIN_CATEGORY_ID")
    private Long mainCategoryId;

    @Column(name = "SUB_CATEGORY_NAME")
    private String subCategoryName;

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
    private MainCategoryEntity mainCategoryEntity;

    @OneToMany(mappedBy = "subCategoryEntity")
    private List<InterestCategoryEntity> interestCategoryEntityList;
}
