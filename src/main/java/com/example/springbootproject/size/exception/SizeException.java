package com.example.springbootproject.size.exception;

import lombok.*;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@Builder
public class SizeException extends RuntimeException{

    private SizeErrorCode errorCode;
    private String errorMessage;

    public SizeException(SizeErrorCode errorCode){
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }
}
