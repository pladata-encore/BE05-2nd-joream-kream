package com.example.springbootproject.pointHistory.dto.response;

import com.example.springbootproject.pointHistory.domain.PointHistory;
import lombok.Builder;

@Builder
public record GetPointResponse(int balance, boolean transactionType ,int transactionVolume) {

    public static GetPointResponse from(PointHistory pointHistory){
        return GetPointResponse.builder()
                .balance(pointHistory.getBalance())
                .transactionType(pointHistory.getTransactionType())
                .transactionVolume(pointHistory.getTransactionVolume())
                .build();
    }
}
