package com.example.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatroomDTO {
    private List<ChatroomMemberDTO> chatroomMemberDTOList;
    private String resultCode;
    private String resultMessage;
}
