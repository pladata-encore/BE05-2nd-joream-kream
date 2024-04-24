package com.example.springbootproject.product.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductErrorResponse {
    private ProductErrorCode errorCode;
    private String errorMessage;
}
