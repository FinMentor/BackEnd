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
@Table(name = "MAIN_CATEGORY")
public class MainCategoryEntity {
    @Id
    @Column(name = "MAIN_CATEGORY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mainCategoryId;

    @Column(name = "MAIN_CATEGORY_NAME")
    private String mainCategoryName;

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

    @OneToMany(mappedBy = "mainCategoryEntity")
    private List<SubCategoryEntity> subCategoryEntityList;
}
