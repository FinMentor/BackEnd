package com.example.springboot.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class ResponseResult<T> {
    private String resultCode;
    private T result;
    private ResponseEntity response;

    // 작업을 실패할 경우
    public static <T> ResponseResult<T> error(T result) {
        return new ResponseResult<>(ResultCodeEnum.FAIL.getValue(), result, ResponseEntity.ok(ResultCodeEnum.FAIL.getMessage()));
    }

    // 작업을 성공할 경우
    public static <T> ResponseResult<T> success(T result) {
        return new ResponseResult<>(ResultCodeEnum.SUCCESS.getValue(), result, ResponseEntity.ok(ResultCodeEnum.SUCCESS.getMessage()));
    }
}
