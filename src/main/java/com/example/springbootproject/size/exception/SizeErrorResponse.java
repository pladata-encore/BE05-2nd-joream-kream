package com.example.springbootproject.size.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SizeErrorResponse {
    private SizeErrorCode errorCode;
    private String errorMessage;
}
