package com.example.springbootproject.orderHistory.dto.response;

import com.example.springbootproject.orderHistory.domain.OrderHistory;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record OrderHistoryResponse(String productName, Long productPrice , String productSize, boolean isSell, LocalDateTime createdAt) {




    public static OrderHistoryResponse from(OrderHistory orderHistory, Long myId){
        return OrderHistoryResponse.builder()
                .productName(orderHistory.getProductName())
                .productPrice(orderHistory.getProductPrice())
                .productSize(orderHistory.getProductSize())
                .createdAt(orderHistory.getCreatedAt())
                .isSell(orderHistory.getSellerId().equals(myId))
                .build();
    }
}
