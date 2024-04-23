package com.example.springbootproject.pointHistory.excrption;

import lombok.*;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@Builder
public class PointException extends RuntimeException{

    private PointErrorCode errorCode;
    private String errorMessage;

    public PointException(PointErrorCode errorCode){
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }
}
