package com.example.springboot.vo;

import com.example.springboot.entity.domain.ChatroomEntity;
import com.example.springboot.entity.common.CommonColumn;
import com.example.springboot.entity.domain.MemberEntity;
import com.example.springboot.entity.domain.MessageEntity;
import com.example.springboot.vo.id.ChatroomGroupId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(ChatroomGroupId.class)
@Table(name = "CHATROOM_GROUP")
public class ChatroomGroupVO extends CommonColumn {
    @Id
    @Comment("멤버 아이디")
    @Column(name = "MEMBER_ID", length = 50, nullable = false, insertable = false, updatable = false)
    private String memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private MemberEntity memberEntity;

    @Id
    @Comment("채팅방 아이디")
    @Column(name = "CHATROOM_ID", nullable = false, insertable = false, updatable = false)
    private Long chatroomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHATROOM_ID")
    private ChatroomEntity chatroomEntity;

    @OneToMany(mappedBy = "chatroomGroupVO", fetch = FetchType.LAZY)
    private List<MessageEntity> messageEntityList;
}
