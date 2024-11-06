package com.example.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class MemberSmsId implements Serializable {
    private String phoneFirst;
    private String phoneMiddle;
    private String phoneLast;
}
