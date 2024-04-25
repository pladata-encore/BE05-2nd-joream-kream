package com.example.springbootproject.sell.exception;

import lombok.*;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@Builder
public class SellException extends RuntimeException{

    private SellErrorCode errorCode;
    private String errorMessage;

    public SellException(SellErrorCode errorCode){
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }
}
