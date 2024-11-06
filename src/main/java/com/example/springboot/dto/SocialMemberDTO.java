package com.example.springboot.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SocialMemberDTO {
    private String memberId;
    private String phoneFirst;
    private String phoneMiddle;
    private String phoneLast;
    private String name;
    private String email;
    private String sns;
}
