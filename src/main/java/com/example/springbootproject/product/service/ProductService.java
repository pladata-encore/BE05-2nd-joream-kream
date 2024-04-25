package com.example.springbootproject.product.service;

import com.example.springbootproject.product.domain.Product;
import com.example.springbootproject.product.dto.response.ProductDetailResponse;
import com.example.springbootproject.product.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> findAll();
    ProductDetailResponse findById(Long id);
    List<ProductResponse> findByProductName(String search);
    List<ProductResponse> findByCategoryName(String category);
}
