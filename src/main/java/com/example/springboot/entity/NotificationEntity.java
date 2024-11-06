package com.example.springboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "NOTIFICATION")
public class NotificationEntity {
    @Id
    @Column(name = "NOTIFICATION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long NotificationId;

    @Column(name = "MEMBER_ID")
    private String MemberId;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "MESSAGE_TYPE")
    private Character messageType;

    @Column(name = "READED")
    private Character readed;

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
