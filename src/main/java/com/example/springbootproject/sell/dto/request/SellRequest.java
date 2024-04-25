package com.example.springbootproject.sell.dto.request;

public record SellRequest(
        Long price,
        Integer duration,
        Long userId
) {
}
