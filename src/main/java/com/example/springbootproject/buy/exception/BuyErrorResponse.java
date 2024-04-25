package com.example.springbootproject.buy.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuyErrorResponse {
    private BuyErrorCode errorCode;
    private String errorMessage;
}
