package com.example.springbootproject.product.repository;

import com.example.springbootproject.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByBrand_Id(Long brandId);

    //엔티티의 fetch가 LAZY여야 할 때 쿼리메서드를 직접 만들어 리턴
    @Query("select p from Product p " +
            "join fetch p.brand b where p.id = :Id")
    Optional<Product> findById(Long Id);
    @Query("select p from Product p " +
            "join fetch p.brand b where p.name like concat('%', :productName, '%') ")
    List<Product> findByNameContaining(String productName);

    @Query("select p from Product p " +
            "join fetch p.brand b where p.category like concat('%', :category, '%') ")
    List<Product> findByCategoryContaining(String category);
}
