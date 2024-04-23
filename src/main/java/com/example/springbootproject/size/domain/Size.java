package com.example.springbootproject.size.domain;

import com.example.springbootproject.product.domain.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Builder
@Table(name = "SIZES")
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SIZE_ID")
    private Long id;

    @Column(name="SIZE_VALUE")
    private String sizeValue;

    @JoinColumn(name = "PRODUCT_ID")
    @ManyToOne
    private Product product;
}
