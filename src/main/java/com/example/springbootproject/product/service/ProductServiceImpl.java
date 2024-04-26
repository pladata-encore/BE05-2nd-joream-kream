package com.example.springbootproject.product.service;

import com.example.springbootproject.product.domain.Product;
import com.example.springbootproject.product.dto.response.ProductDetailResponse;
import com.example.springbootproject.product.dto.response.ProductResponse;
import com.example.springbootproject.product.exception.ProductErrorCode;
import com.example.springbootproject.product.exception.ProductException;
import com.example.springbootproject.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    // 전체 조회
    @Override
    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream().map(ProductResponse::from).toList();
    }

    // 특정 상품 상세
    @Override
    public ProductDetailResponse findById(Long id) {
        Optional<Product> byId = productRepository.findById(id);
        Product product = byId.orElseThrow(
            () -> new ProductException(ProductErrorCode.PRODUCT_NOT_FOUND));
        return ProductDetailResponse.from(product);
    }

    @Override
    public List<ProductResponse> findByProductName(String search) {
        return productRepository.findByNameContaining(search).stream().map(ProductResponse::from).toList();
    }

    @Override
    public List<ProductResponse> findByCategoryName(String category) {
        return productRepository.findByCategoryContaining(category).stream().map(ProductResponse::from).toList();
    }
}
