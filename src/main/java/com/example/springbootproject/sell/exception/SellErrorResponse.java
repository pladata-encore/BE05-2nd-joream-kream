package com.example.springbootproject.sell.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellErrorResponse {
    private SellErrorCode errorCode;
    private String errorMessage;
}
