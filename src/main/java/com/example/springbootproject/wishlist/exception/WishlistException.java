package com.example.springbootproject.wishlist.exception;

import lombok.*;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@Builder
public class WishlistException extends RuntimeException{

    private WishlistErrorCode errorCode;
    private String errorMessage;

    public WishlistException(WishlistErrorCode errorCode){
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }
}
