package com.example.springbootproject.wishlist.dto.response;

import com.example.springbootproject.wishlist.domain.Wishlist;

public record WishlistResponse(
        Long id,
        WishUserInfo user,
        WishProductInfo product
) {
    public static WishlistResponse from(Wishlist wishlist) {
        WishUserInfo uInfo = new WishUserInfo(
                wishlist.getUser().getId(),
                wishlist.getUser().getUsername()
        );

        WishProductInfo pInfo = new WishProductInfo(
                wishlist.getProduct().getId(),
                wishlist.getProduct().getName(),
                wishlist.getProduct().getCategory(), // 보류
                wishlist.getProduct().getBrand(),
                wishlist.getProduct().getColor()
        );

        WishlistResponse response = new WishlistResponse(wishlist.getId(), uInfo, pInfo);
        return response;
    }
}
