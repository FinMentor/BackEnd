package com.example.springboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "CHATROOM")
public class ChatroomEntity {
    @Id
    @Column(name = "CHATROOM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatroomId;

    @Column(name = "MEMBER_ID")
    private String memberId;

    @Column(name = "SUBJECT")
    private String subject;

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

    @OneToMany(mappedBy = "chatroomEntity")
    private List<MessageEntity> messageEntityList;

    @ManyToOne
    private MemberEntity memberEntity;
}
