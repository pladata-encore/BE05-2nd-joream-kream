package com.example.springbootproject.global.exception;

import com.example.springbootproject.auth.excrption.AuthErrorResponse;
import com.example.springbootproject.auth.excrption.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
@ExceptionHandler(AuthException.class)
    public ResponseEntity<AuthErrorResponse> handleAuthException (AuthException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(AuthErrorResponse.builder()
                        .errorCode(e.getErrorCode())
                        .errorMessage(e.getErrorMessage())
                        .build());
    }




}
