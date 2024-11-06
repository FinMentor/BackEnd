package com.example.springboot.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TermsAgreementDTO {
    private Long termsOfUseId;
    private Character required;
}