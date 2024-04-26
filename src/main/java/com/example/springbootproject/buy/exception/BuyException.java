package com.example.springbootproject.buy.exception;

import lombok.*;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@Builder
public class BuyException extends RuntimeException{

    private BuyErrorCode errorCode;
    private String errorMessage;

    public BuyException(BuyErrorCode errorCode){
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }
}
