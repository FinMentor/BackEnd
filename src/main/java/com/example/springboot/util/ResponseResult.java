package com.example.springboot.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class ResponseResult<T> {
    private ResponseEntity responseEntity;

    // 작업을 성공할 경우
    public static <T> ResponseResult<T> success(T result) {
        return new ResponseResult<>(ResponseEntity.ok(result));
    }

    // 작업을 실패할 경우
    public static <T> ResponseResult<T> fail(HttpStatus httpStatus, String resultCode, String resultMessage) {
        return new ResponseResult<>(ResponseEntity.status(httpStatus).body(new ErrorResponse(resultCode, resultMessage)));
    }
}
