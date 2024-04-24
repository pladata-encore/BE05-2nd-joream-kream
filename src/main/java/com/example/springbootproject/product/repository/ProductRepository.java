package com.example.springbootproject.product.repository;

import com.example.springbootproject.brand.dto.response.BrandResponse;
import com.example.springbootproject.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByBrand_Id(Long brandId);
}
