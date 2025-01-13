package com.example.springboot.vo.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberAnswerId implements Serializable {
    private Long memberId;
    private Long questionId;
    private Long questionOptionId;
}
