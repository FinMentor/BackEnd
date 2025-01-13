package com.example.springboot.entity.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberSmsId implements Serializable {
    private String phoneFirst;
    private String phoneMiddle;
    private String phoneLast;
}
