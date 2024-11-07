package com.example.springboot.vo;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@IdClass(MemberCategoryId.class)
@Table(name = "MEMBER_CATEGORY")
public class MemberCategoryVO {
    @Id
    @Column(name = "MEMBER_ID")
    private String memberId;

    @Id
    @Column(name = "MAIN_CATEGORY_ID")
    private String mainCategoryId;

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
}
