package com.example.springboot.entity.domain;

import com.example.springboot.entity.common.CommonColumn;
import com.example.springboot.entity.common.util.ColumnYn;
import com.example.springboot.vo.ChatroomGroupVO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
@Table(name = "MESSAGE")
public class MessageEntity extends CommonColumn {
    @Id
    @Comment("메시지 아이디")
    @Column(name = "MESSAGE_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @Comment("멤버 아이디")
    @Column(name = "MEMBER_ID", length = 50, nullable = false, insertable = false, updatable = false)
    private String memberId;

    @Comment("채팅방 아이디")
    @Column(name = "CHATROOM_ID", nullable = false, insertable = false, updatable = false)
    private Long chatroomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({@JoinColumn(name = "MEMBER_ID"), @JoinColumn(name = "CHATROOM_ID")})
    private ChatroomGroupVO chatroomGroupVO;

    @Comment("내용")
    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Comment("메시지 유형")
    @Column(name = "MESSAGE_TYPE", length = 1, nullable = false)
    @ColumnDefault("'N'")
    @Enumerated(EnumType.STRING)
    private ColumnYn messageType;
}
