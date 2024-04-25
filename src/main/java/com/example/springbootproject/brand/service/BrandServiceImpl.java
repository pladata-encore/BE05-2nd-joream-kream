package com.example.springbootproject.brand.service;

import com.example.springbootproject.product.dto.response.ProductBrandResponse;
import com.example.springbootproject.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final ProductRepository productRepository;
    @Override
    public List<ProductBrandResponse> getAllProductsByBrandId(Long brandId) {
        return productRepository.findAllByBrand_Id(brandId)
                .stream()
                .map(ProductBrandResponse::from)
                .toList()
            ;
    }
}
