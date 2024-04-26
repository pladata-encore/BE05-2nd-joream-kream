package com.example.springbootproject.brand.controller;

import com.example.springbootproject.brand.service.BrandService;
import com.example.springbootproject.product.dto.response.ProductBrandResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/brands")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;
    @GetMapping("/{brandId}")
    public List<ProductBrandResponse> getAllProductsByBrandId(@PathVariable("brandId") Long brandId){
        return brandService.getAllProductsByBrandId(brandId);
    }
}
