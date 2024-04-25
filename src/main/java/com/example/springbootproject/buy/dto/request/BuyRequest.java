package com.example.springbootproject.buy.dto.request;

public record BuyRequest(
        Long price,
        Integer duration,
        Long userId
) {
}
