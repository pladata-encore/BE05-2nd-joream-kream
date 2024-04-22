package com.example.springbootproject.auth.excrption;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum AuthErrorCode {
    PERMISSION_DENIED("권한이 없습니다",HttpStatus.FORBIDDEN);


    private final String description;
    private final HttpStatus httpStatus;
}
