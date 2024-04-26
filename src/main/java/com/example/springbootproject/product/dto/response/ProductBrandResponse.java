package com.example.springbootproject.product.dto.response;

import com.example.springbootproject.product.domain.Product;

import java.util.Date;

public record ProductBrandResponse(String productName, String brandName, String category, Integer release_price, String modelCode, String productColor, Date releasedAt) {
    public static ProductBrandResponse from(Product product) {
        ProductBrandResponse productBrand =
                new ProductBrandResponse(
                        product.getName(),
                        product.getBrand().getBrandName(),
                        product.getCategory(),
                        product.getReleasePrice(),
                        product.getModelCode(),
                        product.getColor(),
                        product.getReleasedAt());
        return productBrand;
    }
}
