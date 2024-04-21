package com.example.springbootproject.brand.repository;

import com.example.springbootproject.brand.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,Long> {
}
