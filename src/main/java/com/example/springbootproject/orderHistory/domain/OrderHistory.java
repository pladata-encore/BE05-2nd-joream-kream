package com.example.springbootproject.orderHistory.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Builder
@Table(name = "ORDER_HISTORYS")
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_HISTORY_ID")
    private Long id;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "PRODUCT_PRICE")
    private Integer productPrice;

    @Column(name = "PRODUCT_SIZE")
    private String productSize;

    @Column(name = "BUYER_ID")
    private Integer buyerId;

    @Column(name = "SELLER_ID")
    private Integer sellerId;

    @Column(name = "CREATED_AT")
    private Date createdAt;
}
