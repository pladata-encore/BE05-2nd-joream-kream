package com.example.springbootproject.product.controller;

import com.example.springbootproject.product.domain.Product;
import com.example.springbootproject.product.dto.response.ProductDetailResponse;
import com.example.springbootproject.product.dto.response.ProductResponse;
import com.example.springbootproject.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    // 이름으로 검색 조회
    @GetMapping(params = "name")
    public List<ProductResponse> searchProductByName(@RequestParam(value = "name", required = false) String name) {
        return productService.findByProductName(name);
    }

    // 카테고리로 검색 조회
    @GetMapping(params = "category")
    public List<ProductResponse> searchProductByCategory(@RequestParam(value = "category", required = false) String category) {
        return productService.findByCategoryName(category);
    }

    // 전체 조회
    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.findAll();
    }

    // 상세 조회
    @GetMapping("/{id}")
    public ProductDetailResponse getProductById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

}
