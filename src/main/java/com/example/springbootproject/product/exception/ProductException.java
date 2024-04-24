package com.example.springbootproject.product.exception;

import lombok.*;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@Builder
public class ProductException extends RuntimeException{

    private ProductErrorCode errorCode;
    private String errorMessage;

    public ProductException(ProductErrorCode errorCode){
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }
}
