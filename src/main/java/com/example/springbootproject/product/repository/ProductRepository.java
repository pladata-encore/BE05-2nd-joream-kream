package com.example.springbootproject.product.repository;

import com.example.springbootproject.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
