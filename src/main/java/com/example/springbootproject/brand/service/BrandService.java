package com.example.springbootproject.brand.service;

import com.example.springbootproject.product.dto.response.ProductBrandResponse;

import java.util.List;

public interface BrandService {
    List<ProductBrandResponse> getAllProductsByBrandId(Long brandId);
}
