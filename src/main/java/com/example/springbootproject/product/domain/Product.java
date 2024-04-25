package com.example.springbootproject.product.domain;

import com.example.springbootproject.brand.domain.Brand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Builder
@Table(name = "PRODUCTS")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;

    @Column(name = "PRODUCT_NAME")
    private String name;

    @Column(name = "PRODUCT_CATEGORY")
    private String category;

    @JoinColumn(name = "BRAND_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Brand brand;

    @Column(name = "RELEASE_PRICE")
    private Integer releasePrice;

    @Column(name = "MODEL_CODE")
    private String modelCode;

    @Column(name = "PRODUCT_COLOR")
    private String color;

    @Column(name = "RELEASED_AT")
    private Date releasedAt;
}
