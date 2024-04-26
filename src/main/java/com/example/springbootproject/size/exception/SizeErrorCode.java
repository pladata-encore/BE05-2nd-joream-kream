package com.example.springbootproject.size.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum SizeErrorCode {
    SIZE_NOT_FOUND("해당 사이즈가 존재하지 않습니다.",HttpStatus.NOT_FOUND);

    private final String description;
    private final HttpStatus httpStatus;
}
