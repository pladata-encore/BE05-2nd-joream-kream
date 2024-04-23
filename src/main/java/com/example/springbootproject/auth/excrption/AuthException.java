package com.example.springbootproject.auth.excrption;

import lombok.*;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@Builder
public class AuthException extends RuntimeException{

    private AuthErrorCode errorCode;
    private String errorMessage;

    public AuthException (AuthErrorCode errorCode){
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }
}
