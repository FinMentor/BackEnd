package com.example.springboot.entity.common.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ColumnYn {
    Y("Y", true),

    N("N", false);

    private final String code;

    private final boolean enabled;
}
