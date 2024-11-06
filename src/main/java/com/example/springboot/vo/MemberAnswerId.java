package com.example.springboot.vo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class MemberAnswerId implements Serializable {
    private String memberId;
    private Long questionId;
    private Long questionOptionId;
}
