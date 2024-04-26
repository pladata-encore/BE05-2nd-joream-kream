package com.example.springbootproject.wishlist.service;

import com.example.springbootproject.wishlist.dto.request.WishlistRequest;
import com.example.springbootproject.wishlist.dto.response.WishlistResponse;

import java.util.List;

public interface WishlistService {

    List<WishlistResponse> getAllWishlist(/*Long uid*/String token);

    void addOrDeleteWishlist(String token, WishlistRequest request);
}
