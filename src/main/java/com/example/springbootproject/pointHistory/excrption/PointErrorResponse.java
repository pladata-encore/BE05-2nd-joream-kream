package com.example.springbootproject.pointHistory.excrption;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PointErrorResponse {
    private PointErrorCode errorCode;
    private String errorMessage;
}
