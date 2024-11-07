package com.example.springboot.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberCategoryId implements Serializable {
    private String memberId;
    private Long mainCategoryId;
}
