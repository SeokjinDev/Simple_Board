package com.practice.simple_board.common.advice;

import com.practice.simple_board.common.exception.BaseException;
import com.practice.simple_board.common.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionAdvice {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse> handleGlobalException(BaseException exception) {
        return ResponseEntity.status(exception.getStatusCode())
                .body(ApiResponse.fail(exception.getStatusCode(), exception.getMessage()));
    }
}
