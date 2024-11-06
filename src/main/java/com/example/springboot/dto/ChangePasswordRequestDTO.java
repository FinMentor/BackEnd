package com.example.springboot.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ChangePasswordRequestDTO {
    private String memberId;
    private String password;
    private String passwordConfirmation;
}
