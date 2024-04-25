package com.example.springbootproject.product.controller;

import com.example.springbootproject.product.domain.Product;
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

    // 전체 조회
    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.findAll();
    }

    // 상세 조회
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    // 검색 조회
    @GetMapping("/search")
    public List<ProductResponse> searchProductByName(@RequestParam("search") String search) {
        return productService.findByProductName(search);
    }
}
