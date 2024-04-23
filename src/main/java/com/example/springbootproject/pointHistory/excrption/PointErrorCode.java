package com.example.springbootproject.pointHistory.excrption;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum PointErrorCode {
    USER_NOT_FOUND("해당 회원은 존재하지 않습니다",HttpStatus.NOT_FOUND);



    private final String description;
    private final HttpStatus httpStatus;
}
