package com.example.springbootproject.product.dto.response;

import com.example.springbootproject.brand.domain.Brand;
import com.example.springbootproject.product.domain.Product;

public record ProductResponse(
        Long productId,
        String productName,
        Brand brand,
        Integer releasePrice
) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getBrand(),
                product.getReleasePrice()
        );
    }
}
