package com.example.springbootproject.wishlist.dto.response;

import com.example.springbootproject.wishlist.domain.Wishlist;

public record WishlistResponse(
        Long id,
        WishSizeInfo size
) {
    public static WishlistResponse from(Wishlist wishlist) {

        WishSizeInfo sInfo = new WishSizeInfo(
                wishlist.getSize().getId(),
                wishlist.getSize().getSizeValue(),
                wishlist.getSize().getProduct()
        );

        return new WishlistResponse(wishlist.getId(), sInfo);
    }
}
