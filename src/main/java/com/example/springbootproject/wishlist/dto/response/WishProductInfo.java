package com.example.springbootproject.wishlist.dto.response;

import com.example.springbootproject.brand.domain.Brand;

public record WishProductInfo (
        Long productId,
        String productName,
        String productCategory,
        Brand brand,
        String color
){
}
