package com.example.springbootproject.wishlist.domain;

import com.example.springbootproject.auth.domain.Auth;
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
@Table(name = "WISHLIST")
public class Wishlist {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WISHLIST_ID")
    private Long id;

    @JoinColumn(name = "AUTH_ID")
    @ManyToOne
    private Auth auth;

    @JoinColumn(name = "PRODUCT_ID")
    @ManyToOne
    private Product product;

}
