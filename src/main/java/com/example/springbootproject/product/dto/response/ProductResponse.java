package com.example.springbootproject.product.dto.response;

import com.example.springbootproject.product.domain.Product;

public record ProductResponse(
        Long productId,
        String productName,
        String brandName,
        Integer releasePrice
) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getBrand().getBrandName(),
                product.getReleasePrice()
        );
    }
}
