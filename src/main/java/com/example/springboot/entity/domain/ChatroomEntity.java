package com.example.springboot.entity.domain;

import com.example.springboot.entity.common.CommonColumn;
import com.example.springboot.vo.ChatroomGroupVO;
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
@Table(name = "CHATROOM")
public class ChatroomEntity extends CommonColumn {
    @Id
    @Comment("채팅방 아이디")
    @Column(name = "CHATROOM_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatroomId;

    @OneToMany(mappedBy = "chatroomEntity", fetch = FetchType.LAZY)
    private List<ChatroomGroupVO> chatroomGroupVOList;

    @OneToMany(mappedBy = "chatroomEntity", fetch = FetchType.LAZY)
    private List<MessageEntity> messageEntityList;

    @Comment("제목")
    @Column(name = "SUBJECT", length = 10, nullable = false)
    private String subject;
}
