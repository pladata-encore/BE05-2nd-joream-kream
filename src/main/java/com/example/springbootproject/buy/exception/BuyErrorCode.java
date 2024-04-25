package com.example.springbootproject.buy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum BuyErrorCode {
    BUY_NOT_FOUND("해당 구매 내역이 존재하지 않습니다.",HttpStatus.NOT_FOUND);

    private final String description;
    private final HttpStatus httpStatus;
}
