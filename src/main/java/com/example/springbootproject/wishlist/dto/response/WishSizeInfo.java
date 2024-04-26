package com.example.springbootproject.wishlist.dto.response;

import com.example.springbootproject.product.domain.Product;

public record WishSizeInfo(
        Long sizeId,
        String sizeValue,
        Product product
){
}
