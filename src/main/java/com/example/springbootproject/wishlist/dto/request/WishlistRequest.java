package com.example.springbootproject.wishlist.dto.request;

import com.example.springbootproject.auth.domain.User;
import com.example.springbootproject.product.domain.Product;
import com.example.springbootproject.wishlist.domain.Wishlist;

public record WishlistRequest(Long userId, Long productId) {

}
