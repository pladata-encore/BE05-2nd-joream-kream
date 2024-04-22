package com.example.springbootproject.auth.excrption;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthErrorResponse {
    private AuthErrorCode errorCode;
    private String errorMessage;
}
