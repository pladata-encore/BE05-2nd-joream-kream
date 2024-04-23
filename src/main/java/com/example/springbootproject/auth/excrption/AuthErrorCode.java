package com.example.springbootproject.auth.excrption;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum AuthErrorCode {
    RECHARGE_IS_AVAILABLE_FROM_10000_KRW("충전은 10000원부터 가능합니다",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND("해당 회원은 존재하지 않습니다",HttpStatus.NOT_FOUND),
    PERMISSION_DENIED("권한이 없습니다",HttpStatus.FORBIDDEN);


    private final String description;
    private final HttpStatus httpStatus;
}
