package com.example.springboot.vo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class MemberCategoryId implements Serializable {
    private String memberId;
    private Long mainCategoryId;
}
