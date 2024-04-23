package com.example.springbootproject.product.service;

import com.example.springbootproject.product.domain.Product;
import com.example.springbootproject.product.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> findAll();
    Product findById(Long id);
}
