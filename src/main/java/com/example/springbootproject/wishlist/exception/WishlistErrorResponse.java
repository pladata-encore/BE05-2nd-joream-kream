package com.example.springbootproject.wishlist.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WishlistErrorResponse {
    private WishlistErrorCode errorCode;
    private String errorMessage;
}
