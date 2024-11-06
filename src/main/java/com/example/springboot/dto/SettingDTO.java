package com.example.springboot.dto;

import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SettingDTO {
    private String memberId;            // 사용자 ID
    private String modeCode;          // 모드 코드 (예: 다크 모드)
    private String homeNavigationCode; // 홈 화면 설정 코드
    private Timestamp createdAt;       // 생성 시간
    private Timestamp updatedAt;       // 업데이트 시간
}
