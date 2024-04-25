package com.example.springbootproject.product.dto.response;

import com.example.springbootproject.product.domain.Product;
import java.util.Date;

public record ProductDetailResponse (
    Long productId,
    String productName,
    String category,
    String brandName,
    Integer releasePrice,
    String modelCode,
    String color,
    Date releasedAt
){
    public static ProductDetailResponse from(Product product) {
        return new ProductDetailResponse(
            product.getId(),
            product.getName(),
            product.getCategory(),
            product.getBrand().getBrandName(),
            product.getReleasePrice(),
            product.getModelCode(),
            product.getColor(),
            product.getReleasedAt()
        );
    }
}
